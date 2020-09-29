import requests
from bs4 import BeautifulSoup


def split_movies_by_no(title, response):
    result = [] # return value
    movie_no_list = get_movie_no_list(response)
    for movie_no in movie_no_list:
        movies = [item for item in response if item["MovieCode"] == movie_no]
        mtitle = movies[0]["MovieNameKR"]
        if title == mtitle :
            timetables = get_time_table(movies)
            for timetable in timetables:
                dict_theater = {}
                # test code
                dict_theater['title'] = title
                dict_theater['screen'] = timetable[0]
                dict_theater['hall'] = timetable[1]
                dict_theater['time'] = timetable[2]
                dict_theater['count'] = timetable[3]
                dict_theater['total'] = timetable[4]
                result.append(dict_theater)
    return result


def get_movie_no_list(response):
    movie_no_list = []
    for item in response:
        movie_no = item["MovieCode"]
        if movie_no not in movie_no_list:
            movie_no_list.append(movie_no)
    return movie_no_list


def get_time_table(movies):
    tuples = []
    for movie in movies:
        time = movie["StartTime"]
        seats = movie["BookingSeatCount"]
        screen = movie["FilmNameUS"]
        hall = movie["ScreenNameKR"]
        total = movie["TotalSeatCount"]
        tuple = (screen, hall, time, seats, total)
        # print(tuple)  # test code
        tuples.append(tuple)
    return tuples

"""
param:
 detailDivisionCode, cinemaID, date  
"""
def run(title, detailDivisionCode, cinemaID, date):
    cinema_id = "1|{}|{}".format(detailDivisionCode, cinemaID)
    date = "{yyyy}-{mm}-{dd}".format(yyyy=date[:4], mm=date[4:6], dd=date[6:])
    url = "https://www.lottecinema.co.kr/LCWS/Ticketing/TicketingData.aspx"
    dic = {"MethodName": "GetPlaySequence",
           "channelType": "MA",
           "osType": "",
           "osVersion": "",
           "playDate": date,
           "cinemaID": cinema_id,
           "representationMovieCode": ""}
    parameters = {"paramList": str(dic)}
    response = requests.post(url, data=parameters).json()
    movies_response = response['PlaySeqs']['Items']
    result = split_movies_by_no(title, movies_response)
    return result


if __name__ == "__main__":
    test = run('담보', '1', '1013', '20200929') # test run
    print(test)