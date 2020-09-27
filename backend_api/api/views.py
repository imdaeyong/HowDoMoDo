# from django.shortcuts import render
# from django.conf.urls import url
# from api import views
from django.views import View
from django.http import HttpResponse, JsonResponse
from api.crawling.run import getTheaterTimesTables

from api.models import TheatersUrls

def getTimesTables(View):
    brand = 'lotte'
    name = '강동'

    theaters = TheatersUrls.objects.get(brand=brand, name=name)
    test = getTheaterTimesTables(brand=brand, name=name, time='20200927')
    print(test)
    result = {'brand':theaters.brand, 'name':theaters.name, 'url':theaters.url}
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True})