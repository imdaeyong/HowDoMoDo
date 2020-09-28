# from django.test import TestCase

import re
import requests
from bs4 import BeautifulSoup
import time
import json
from selenium import webdriver
from html import unescape

import pandas as pd
import numpy as np

from urllib import request

# lotte
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
