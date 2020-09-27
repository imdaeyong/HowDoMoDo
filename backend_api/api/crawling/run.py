'''
1. 파라미터 (브랜드, 영화관이름, 날) 를 받는다.
2. 브랜드와 영화관이름으로 theaters_urls 테이블을 셀렉한다.
3. 브랜드에 맞는 크롤링을 실행한다.
'''

from bs4 import BeautifulSoup
import time
from selenium import webdriver

def parseLotte(html):
    lotte_theater_info = []  # return list
    soup = BeautifulSoup(html, "lxml")
    body = soup.body

    div_time_select_wrap = body.find('div', attrs={'id': 'timeTable'}).find_all('div',
                                                                                attrs={'class': 'time_select_wrap'})

    for div_time_select in div_time_select_wrap:
        div_movie_info = div_time_select.find('div', attrs={'class': 'list_tit'})

        # grade, title
        grade = div_movie_info.find("span").text
        title = div_movie_info.find("p").text

        # screen
        screen = ",".join([_.text for _ in div_time_select.find_all("ul")[0].find_all("li")])

        # start_time, seat_count, hall
        li_theater_info_list = div_time_select.find_all("ul")[1]

        for li_theater_info in li_theater_info_list:
            theater_info = {}

            dd_theater_info = [_ for _ in li_theater_info.find_all("dd")]
            start_time = dd_theater_info[0].find("strong").text
            end_time = dd_theater_info[0].find("div").text
            seat_split = dd_theater_info[1].text.replace(" ", "").split("/")

            if len(seat_split) > 1:
                seat_count, seat_total = seat_split

            else:
                seat_count, seat_total = seat_split[0], seat_split[0]
            hall = dd_theater_info[2].text
            # build thaeter dict
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

def getTheaterTimesTables(brand, name, time):
    brand = brand.lower()
    if brand == 'lotte':
        # lotter parse
        pass

    elif brand == 'cgv':
        # cgv parse
        pass

    elif brand == 'mege':
        # mega parse
        pass

    else :
        pass

    return 'run test getTimesTables'