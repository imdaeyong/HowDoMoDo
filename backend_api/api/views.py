# from django.shortcuts import render
# from django.conf.urls import url
# from api import views
from django.views import View
from django.http import HttpResponse, JsonResponse
from api.crawling.movies import get_theater_timestables

from api.models import TheatersUrls

def suns(request, title):
    result = {'ps' : 60, 'ns' : 40, 'title': title}

    return JsonResponse(result, json_dumps_params={'ensure_ascii': True})

def get_times_tables(request, brand, name, date, title):
    try:
        theaters = TheatersUrls.objects.get(brand=brand, name=name)
        result = get_theater_timestables(brand=brand, url=theaters.url, title=title, date=date)
        return JsonResponse(result, json_dumps_params={'ensure_ascii': True})
    except Exception as e:
        result = {'status':404, 'message':'Backend 확인 해주세요'}
        return JsonResponse(result, json_dumps_params={'ensure_ascii': True})