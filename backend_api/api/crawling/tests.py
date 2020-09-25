from bs4 import BeautifulSoup
import time
from selenium import webdriver
# from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

def generateLotteTheaterInfo(html):
    lotte_theater_info = []  # 리턴 리스트
    soup = BeautifulSoup(html, "lxml")
    body = soup.body

    div_time_select_wrap = body.find('div', attrs={'id': 'timeTable'}).find_all('div',
                                                                                attrs={'class': 'time_select_wrap'})

    for div_time_select in div_time_select_wrap:
        div_movie_info = div_time_select.find('div', attrs={'class': 'list_tit'})

        # 등급, 제목
        grade = div_movie_info.find("span").text
        title = div_movie_info.find("p").text

        # 스크린
        screen = ",".join([_.text for _ in div_time_select.find_all("ul")[0].find_all("li")])

        # 영화 시간, 좌석, 상영관
        li_theater_info_list = div_time_select.find_all("ul")[1]

        for li_theater_info in li_theater_info_list:
            theater_info = {}  # 리턴에 넣을 딕셔너리

            dd_theater_info = [_ for _ in li_theater_info.find_all("dd")]
            start_time = dd_theater_info[0].find("strong").text
            end_time = dd_theater_info[0].find("div").text
            seat_split = dd_theater_info[1].text.replace(" ", "").split("/")

            if len(seat_split) > 1:
                seat_count, seat_total = seat_split

            else:
                seat_count, seat_total = seat_split[0], seat_split[0]
            hall = dd_theater_info[2].text

            theater_info["grade"] = grade
            theater_info["title"] = title
            theater_info["screen"] = screen
            theater_info["start_time"] = start_time
            theater_info["end_time"] = end_time
            theater_info["seat_count"] = seat_count
            theater_info["seat_total"] = seat_total
            theater_info["hall"] = hall
            lotte_theater_info.append(theater_info)

    return lotte_theater_info


def parseCGV(html):
    cgv_theater_info_dict = {}  # return value
    theater_info_list = []

    soup = BeautifulSoup(html, 'lxml')
    body = soup.body
    li_showtimes = body.find('div', attrs={'class': 'showtimes-wrap'}).find('div',
                                                                            attrs={'class': 'sect-showtimes'}).find(
        'ul').find_all('li', recursive=False)

    for li_showtime in li_showtimes:
        div_info_movie = li_showtime.find('div', attrs={'class': 'info-movie'})
        # grade, title
        grade = div_info_movie.find_all('span')[0].text.strip()
        title = div_info_movie.find('a').text.strip()
        div_type_halls = li_showtime.find_all('div', attrs={'class': 'type-hall'})

        for div_type_hall in div_type_halls:
            div_info_hall = div_type_hall.find('div', attrs={'class': 'info-hall'})
            li_timetables = div_type_hall.find('div', attrs={'class': 'info-timetable'}).find('ul').find_all('li')
            # screen, hall, seat_total
            screen, hall, seat_total = [_.text.strip() for _ in div_info_hall.find_all('li')]
            seat_total = seat_total.replace(' ', '').replace('\n', '')

            for li_timetable in li_timetables:
                theater_info_dict = {}
                a_timetable = li_timetable.find('a')
                # start_time, seat_count
                start_time = a_timetable.find('em').text
                seat_count = a_timetable.find('span').contents[-1]
                # build thaeter dict
                theater_info_dict['grade'] = grade
                theater_info_dict['title'] = title
                theater_info_dict['screen'] = screen
                theater_info_dict['hall'] = hall
                theater_info_dict['seat_total'] = seat_total
                theater_info_dict['start_time'] = start_time
                theater_info_dict['seat_count'] = seat_count
                theater_info_list.append(theater_info_dict)

    cgv_theater_info_dict['theaters'] = theater_info_list
    return cgv_theater_info_dict


def parseMega(html):
    mega_theater_info_dict = {}  # return value
    theater_info_list = []

    soup = BeautifulSoup(html, 'lxml')
    body = soup.body
    div_theater_list = body.find('div', attrs={'id': 'tab02'}).find('div', attrs={'reserve theater-list-box'}).find_all(
        'div', attrs={'class': 'theater-list'})

    for div_theater in div_theater_list:
        div_theater_tit = div_theater.find('div', attrs={'class': 'theater-tit'})
        # title
        title = div_theater_tit.find('a').text
        div_theater_type_boxes = div_theater.find_all('div', attrs={'class': 'theater-type-box'})

        for div_theater_type_boxe in div_theater_type_boxes:
            div_theater_type = div_theater_type_boxe.find('div', attrs={'class': 'theater-type'})
            div_theater_time = div_theater_type_boxe.find('div', attrs={'class': 'theater-time'})
            # hall, seat_total
            hall = div_theater_type.find('p', attrs={'class': 'theater-name'}).text
            seat_total = div_theater_type.find('p', attrs={'class': 'chair'}).text
            # screen
            screen = div_theater_time.find('div', attrs={'class': 'theater-type-area'}).text
            td_theaters = div_theater_time.find('tbody').find('tr').find_all('td', recursive=False)

            for td_theater in td_theaters:
                theater_info_dict = {}
                # start_time, seat_count
                start_time = td_theaters[0].find('p', attrs={'class': 'time'}).text
                seat_count = td_theaters[0].find('p', attrs={'class': 'chair'}).text
                # build theater
                theater_info_dict['title'] = title
                theater_info_dict['hall'] = hall
                theater_info_dict['seat_total'] = seat_total
                theater_info_dict['screen'] = screen
                theater_info_dict['start_time'] = start_time
                theater_info_dict['seat_count'] = seat_count
                theater_info_list.append(theater_info_dict)

    mega_theater_info_dict['theaters'] = theater_info_list
    return mega_theater_info_dict


def test():
    url = "https://www.lottecinema.co.kr/NLCHS/Cinema/Detail?divisionCode=1&detailDivisionCode=1&cinemaID=1013"

    # local code
    chrome_path = "/Users/c0re/Documents/lab/sub2/chromedriver"
    browser = webdriver.Chrome(chrome_path)
    check_elmt = None
    while check_elmt is None:
        browser.get(url)
        time.sleep(3)
        browser.implicitly_wait(10)
        check_elmt = browser.find_element_by_id('contents')


    # # remote code
    # browser = webdriver.Remote('http://127.0.0.1:4444/wd/hub', DesiredCapabilities.CHROME)
    # browser.get(url)

    html = browser.page_source
    # browser.implicitly_wait(5)
    browser.quit()

    theater_info_list = generateLotteTheaterInfo(html)
    return {"theaters":theater_info_list}

def runCGV():
    url = "http://www.cgv.co.kr/theaters/?areacode=01&theaterCode=0056&date=20200925"

    # local code
    chrome_path = "/Users/c0re/Documents/lab/sub2/chromedriver"
    browser = webdriver.Chrome(chrome_path)
    browser.get(url)

    # # remote code
    # browser = webdriver.Remote('http://127.0.0.1:4444/wd/hub', DesiredCapabilities.CHROME)
    # browser.get(url)

    ifrm_movie_time_table = browser.find_element_by_id("ifrm_movie_time_table")
    browser.switch_to_frame(ifrm_movie_time_table)
    html = browser.page_source
    time.sleep(5)
    # browser.implicitly_wait(5)
    browser.quit()

    theater_info_list = parseCGV(html)
    return theater_info_list