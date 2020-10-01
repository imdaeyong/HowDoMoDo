from django.shortcuts import render

# Create your views here.

from django.http import HttpResponse, JsonResponse
from . import recommand_activity
import time

def create_load(request):

    # 정적 메소드 및 클래스 변수 확인
    # print(recommand_activity.Recommand.testnum)
    # recommand_activity.Recommand.test()
    # print(recommand_activity.Recommand.testnum)
    recommand_activity.Recommand.create()
    recommand_activity.Recommand.load_csv()

    test = {"A": [1, 2], "B": [3, 4]}
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})

def preProcess(request):
    start = time.time()
    recommand_activity.Recommand.preprocessing(recommand_activity.Recommand.df_list)
    preAnalysis()
    test = {"A": [1, 2], "B": [3, 4]}
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})

def preAnalysis():
    start = time.time()
    si_list = ['강남구', '종로구','군포시']
    for si in si_list:
        recommand_activity.Recommand.find_si(si)
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간

def find_si(request, siname):
    print(siname)
    start = time.time()
    df = recommand_activity.Recommand.find_si(siname)
    storedict = recommand_activity.Recommand.df_to_dict(df)
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(storedict,   json_dumps_params={'ensure_ascii': False})
