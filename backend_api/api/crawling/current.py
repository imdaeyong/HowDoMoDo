import requests
from bs4 import BeautifulSoup
import re


def get_current_movie_list() :
    result = []

    res = requests.get("https://movie.naver.com/movie/running/current.nhn")
    soup = BeautifulSoup(res.content, 'lxml')
    body = soup.body
    li_current_movie = body.select("div#content > div > div.obj_section > div.lst_wrap > ul > li")
    for current_movie in li_current_movie:
        movie = {}
        poster_path = current_movie.find("img")["src"].replace("m99_141_2", "f125") # resieze: m99_141_2 -> f125
        current_movie_dsc = current_movie.find("dl", attrs={"class":"lst_dsc"})
        if current_movie_dsc.select_one("dt.tit > span") is None :
            age = "전체"
        else :
            grade = current_movie_dsc.select_one("dt.tit > span").text
            if grade == "청소년 관람불가" :
                age = "청불"
            elif grade == "전체 관람가" :
                age = "전체"
            else :
                age = re.search(r'[0-9]*', current_movie_dsc.select_one("dt.tit > span").text)[0]
        title = current_movie_dsc.select_one("dt.tit > a").text
        movie_id = re.search(r'.*code=([0-9]*)', current_movie_dsc.select_one("dt.tit > a")["href"])[1]
        genre = ",".join([_.text for _ in current_movie_dsc.select_one("dd > dl.info_txt1 > dd").select("span > a")])
        vote_average = current_movie_dsc.select_one("dd > dl > dd > div > a > span.num").text

        movie["title"] = title
        movie["genreIds"] = genre
        movie["age"] = age
        movie["voteAverage"] = vote_average
        movie["posterPath"] = poster_path
        movie["id"] = movie_id
        result.append(movie)
    return result