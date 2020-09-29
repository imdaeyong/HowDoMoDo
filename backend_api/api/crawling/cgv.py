import requests
from bs4 import BeautifulSoup


def get_timetable(movie):
    tuples = []
    timetables = movie.select('div > div.type-hall > div.info-timetable > ul > li')
    for timetable in timetables:
        time = timetable.select_one('a > em').text if timetable.select_one('a > em') is not None else ''
        seat = timetable.select_one('a > span').text if timetable.select_one('a > span') is not None else ''
        tuple = (time, seat)
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
                dict_theater['time'] = timetable[0]
                dict_theater['count'] = timetable[1]
                result.append(dict_theater)

    # print(result)
    return result


if __name__ == "__main__":
    test = run('그린랜드', '01', '0056', '20200928') # test run
    print(test)