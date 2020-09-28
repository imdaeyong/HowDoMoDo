'''
1. 파라미터 (브랜드, 영화관이름, 날) 를 받는다.
2. 브랜드와 영화관이름으로 theaters_urls 테이블을 셀렉한다.
3. 브랜드에 맞는 크롤링을 실행한다.
'''

from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.common.keys import Keys

import time as time
import platform
import logging

logger = logging.getLogger(__name__)


def get_browser():
    # local browser path
    local_path = '/Users/c0re/Documents/lab/sub2/phantomjs-2.1.1-macosx/bin/phantomjs'
    remote_path = "/home/ubuntu/selenium/phantomjs-2.1.1-linux-x86_64/bin/phantomjs"
    system = platform.system()

    if system == 'Linux':
        return webdriver.PhantomJS(remote_path)
        # return webdriver.Remote('http://127.0.0.1:4444/wd/hub', DesiredCapabilities.CHROME)
    else :
        return webdriver.PhantomJS(local_path)


def get_brand_url(brand, url):
    brand_url = ''
    if brand == 'lotte':
        brand_url = 'https://www.lottecinema.co.kr/NLCHS/Cinema/Detail?'

    elif brand == 'cgv':
        brand_url = 'http://www.cgv.co.kr/'

    elif brand == 'mega':
        brand_url = 'https://www.megabox.co.kr/'

    return brand_url + url


def parse_lotte(html):
    lotte_theater_info = []  # return list
    soup = BeautifulSoup(html, "lxml")
    body = soup.body

    div_time_select_wrap = body.find('div', attrs={'id': 'timeTable'})\
                                .find_all('div',attrs={'class': 'time_select_wrap'})

    for div_time_select in div_time_select_wrap:
        div_movie_info = div_time_select.find('div', attrs={'class': 'list_tit'})

        # grade, title
        grade = div_movie_info[0].find("span").text
        title = div_movie_info[0].find("p").text
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
            print('dict: {}'.format(theater_info))
            lotte_theater_info.append(theater_info)

    return lotte_theater_info


def parse_cgv(html):
    cgv_theater_info_dict = {}  # return value
    theater_info_list = []

    soup = BeautifulSoup(html, 'lxml')
    body = soup.body
    li_showtimes = body.find('div', attrs={'class': 'showtimes-wrap'})\
                        .find('div', attrs={'class': 'sect-showtimes'})\
                        .find('ul').find_all('li', recursive=False)

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

def parse_mega(html):
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
                start_time = td_theater.find('p', attrs={'class': 'time'}).text
                seat_count = td_theater.find('p', attrs={'class': 'chair'}).text
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


def parse_naver(brand, name, title, day):
    # brand(lotte, mega)
    brand = brand.lower()
    # re brand, name
    if brand == 'lotte':
        brand = '롯데시네마'
        name = brand+'-'+name
    else :
        brand = '메가박스'
        name = brand+' '+name

    url = 'https://ticket.movie.naver.com/Ticket/Reserve.aspx'
    browser = get_browser()

    try :

        browser.get(url)
        browser.implicitly_wait(10)

        # step1. movie choice
        # step1-1. get movie list
        html = browser.page_source
        soup = BeautifulSoup(html, 'lxml')
        body = soup.body
        movie_title_list = [_['title'] for _ in body.find('div', attrs={'id':'mlist'}).find('ul').find_all('li', recursive=False)]

        if title in movie_title_list:
            mIdx = movie_title_list.index(title)
        else:
            mIdx = 0 # testing code

        elmt_mlist = browser.find_elements_by_xpath("//div[@id='mlist']/ul/li")
        # step1-2. movie click
        elmt_mlist[mIdx].click()

        # step2. theater choice
        # step2-1. theater list
        html = browser.page_source
        soup = BeautifulSoup(html, 'lxml')
        body = soup.body
        dl_area_list = body.find('div', attrs={'id':'area_tlist'}).find_all('dl')

        areaIdx = 0
        theaterIdx = 0
        for idx, dl_area in enumerate(dl_area_list):
            area = dl_area.find('dt').text
            li_theaters = [_.find('em').contents[0] for _ in dl_area.find('dd').find('ul').find_all('li')]

            if name in li_theaters:
                areaIdx = idx
                theaterIdx = li_theaters.index(name)
        # step2-2. theater click
        browser.find_elements_by_xpath("//div[@id='area_tlist']/dl")[areaIdx].find_elements_by_xpath("/dd/ul/li")[theaterIdx].click()

        # step3. date choice
        html = browser.page_source
        soup = BeautifulSoup(html, 'lxml')
        body = soup.body
        tr_list = body.find('div', attrs={'id':'calendar'}).find('table').find('tbody').find_all('tr')
        weekIdx = 0
        for idx, tr_calendar in enumerate(tr_list):
            tr_calendar.find_all('td', attrs={''})
            if len(tr_calendar) > 0:
                weekIdx = idx
                day_list = ['{0:>02s}'.format(_.find('a').text) for _ in tr_list[weekIdx].find_all('td', attrs={'class': 'enable'})]
                dayIdx = day_list.index(day[-2:])
                break

        if 'day_list' in globals():
            # date click
            browser.find_elements_by_xpath("//div[@id='calendar']/table/tbody/tr")[weekIdx].find_elements_by_xpath('td')[dayIdx].click()

        html = browser.page_source
        print(html)

        return {'titles' : movie_title_list}

    finally:
        browser.quit()


def get_theater_timestables(brand, url, stime):

    brand = brand.lower()
    url = get_brand_url(brand, url)
    result = None
    browser = get_browser()
    browser.implicitly_wait(5)

    try:
        if brand == 'cgv':

            if url[-1] != '=' :
                pass

            else :
                url += stime
                check_elmt = None

                while check_elmt is None:
                    browser.get(url)
                    time.sleep(1)
                    browser.implicitly_wait(10)
                    ifrm_movie_time_table = browser.find_element_by_id("ifrm_movie_time_table")
                    browser.switch_to_frame(ifrm_movie_time_table)
                    browser.implicitly_wait(10)
                    check_elmt = browser.find_elements_by_class_name('info-timetable')

                html = browser.page_source
                result = parse_cgv(html)
                return result

        elif brand == 'lotte':
            # 계속 자바스크립트 모두 실행된 html 을 못받는 이슈!
            check_elmt = None

            while check_elmt is None:
                browser.get(url)
                time.sleep(3)
                browser.implicitly_wait(10)
                check_elmt = browser.find_element_by_id('contents')

            html = browser.page_source
            result = parse_lotte(html)

        elif brand == 'mega':
            check_elmt = None

            while check_elmt is None:
                browser.get(url)
                time.sleep(1)
                browser.implicitly_wait(10)
                check_elmt = browser.find_element_by_id('contents')

            html = browser.page_source
            result = parse_mega(html)

        return result

    except Exception as e:
        print(e)
        logger.debug(e)

    finally:
        browser.quit()

def get_tester():
    result = parse_naver('lotte', '강동', '테넷', '20200928')
    return result