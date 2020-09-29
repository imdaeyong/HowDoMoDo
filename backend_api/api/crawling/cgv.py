import re
import requests
from bs4 import BeautifulSoup


def get_timetable(movie):
    tuples = []
    info_halls = movie.select('div > div.type-hall')
    for info_hall in info_halls:
        info = info_hall.select('div.info-hall > ul > li')
        screen = info[0].text.strip()
        hall = info[1].text.strip()
        total = re.findall("\d+",info[2].text)[0]
        timetables = movie.select('div.info-timetable > ul > li')
        for timetable in timetables:
            time = timetable.select_one('a > em').text if timetable.select_one('a > em') is not None else ''
            seat = re.findall("\d+", timetable.select_one('a > span').text)[0] if timetable.select_one('a > span') is not None else ''
            tuple = (screen, hall, time, seat, total)
            # print(tuple) # test print
            if time != '' : # append if not sold
                tuples.append(tuple)
    return tuples

"""
param:
 areacode, theatercode, date  
"""
def run(title, areacode, theatercode, date):
    result = [] # return value
    url = "http://www.cgv.co.kr/common/showtimes/iframeTheater.aspx?areacode={}&theatercode={}&date={}".format(areacode, theatercode, date)
    response = requests.get(url)
    html = response.text
    soup = BeautifulSoup(html,'html.parser')
    movies = soup.select('body > div > div.sect-showtimes > ul > li')

    for movie in movies:
        div_title = movie.select_one('div > div.info-movie > a > strong').get_text().strip()
        timetables = get_timetable(movie)
        if title == div_title:
            for timetable in timetables:
                dict_theater = {}
                dict_theater['title'] = title
                dict_theater['screen'] = timetable[0]
                dict_theater['hall'] = timetable[1]
                dict_theater['time'] = timetable[2]
                dict_theater['count'] = timetable[3]
                dict_theater['total'] = timetable[4]
                result.append(dict_theater)

    # print(result)
    return result


if __name__ == "__main__":
    test = run('그린랜드', '01', '0056', '20200928') # test run
    print(test)