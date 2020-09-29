import requests
from bs4 import BeautifulSoup


def get_movie_no_list(response):
    movie_no_list = []
    for item in response:
        movie_no = item["movieNo"]
        if movie_no not in movie_no_list:
            movie_no_list.append(movie_no)
    return movie_no_list


def split_movies_by_no(title, response):
    result = []
    movie_no_list = get_movie_no_list(response)
    for movie_no in movie_no_list:
        movies = [item for item in response if item["movieNo"] == movie_no]
        mtitle = movies[0]["movieNm"]
        if title == mtitle:
            timetables = get_time_table(movies)
            for timetable in timetables:
                dict_theater = {}
                dict_theater['title'] = title
                dict_theater['time'] = timetable[0]
                dict_theater['count'] = timetable[1]
                result.append(dict_theater)
    return result


def get_movie_no_list(response):
    movie_no_list = []
    for item in response:
        movie_no = item["movieNo"]
        if movie_no not in movie_no_list:
            movie_no_list.append(movie_no)
    return movie_no_list


def get_time_table(movies):
    tuples = []
    for movie in movies:
        time = movie["playStartTime"]
        seats = movie["restSeatCnt"]
        tuple = (time, seats)
        tuples.append(tuple)
    return tuples

"""
param:
 brchNo, date  
"""
def run(title, brchNo, date):
    url = "https://www.megabox.co.kr/on/oh/ohc/Brch/schedulePage.do"
    parameters = {"masterType": "brch",
                  "detailType": "area",
                  "brchNo": brchNo,
                  "firstAt": "N",
                  "brchNo1": brchNo,
                  "crtDe": date,
                  "playDe": date}
    response = requests.post(url, data=parameters).json()
    movie_response = response['megaMap']['movieFormList']
    return split_movies_by_no(title, movie_response)


if __name__ == "__main__":
    test = run('그린랜드', '1372', '20200929') # test run
    print(test)