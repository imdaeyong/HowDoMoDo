# from django.shortcuts import render
# from django.conf.urls import url
# from api import views
from django.views import View
from django.http import HttpResponse, JsonResponse
from .crawling.tests import test, runCGV
from .crawling import run

def test1(View):
    theaters = test()
    return JsonResponse(theaters, json_dumps_params={'ensure_ascii': True})

class getTheaterInfo(View):
    # brand
    # theater
    # date
    pass