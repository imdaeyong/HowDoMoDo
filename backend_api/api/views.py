# from django.shortcuts import render
# from django.conf.urls import url
# from api import views
from django.views import View
from django.http import HttpResponse, JsonResponse
from api.crawling.run import get_theater_timestables, get_tester

from api.models import TheatersUrls

def get_times_tables(request, brand, name, stime):
    theaters = TheatersUrls.objects.get(brand=brand, name=name)
    result = get_theater_timestables(brand=brand, url=theaters.url, stime=stime)
    result = {'theater': None} if result is None else result
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True})

def tester(request):
    result = get_tester()
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True})