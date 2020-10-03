
from django.http import HttpResponse, JsonResponse
from . import recommand_activity
import time

def create_load(request):

    # 정적 메소드 및 클래스 변수 확인

    recommand = recommand_activity.Recommand()
    print(recommand)
    recommand.create()
    recommand.load_csv()
    test = {"A": [1, 2], "B": [3, 4], "C": str(recommand)}
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})

def preProcess(request):
    start = time.time()
    recommand = recommand_activity.Recommand()
    recommand.preprocessing()
    print(recommand)
    preAnalysis()
    test = {"A": [10, 20], "B": [30, 40], "C": str(recommand)}
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})

def preAnalysis():
    start = time.time()
    si_list = ['강남구', '종로구', '군포시']
    recommand = recommand_activity.Recommand()
    print(recommand)

    for si in si_list:
        recommand.find_si(si)
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간

def find_si(request, siname):
    print(siname)
    start = time.time()
    recommand = recommand_activity.Recommand()
    print(recommand)
    df = recommand.find_si(siname)
    storedict = recommand_activity.Recommand.df_to_dict(df)
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(storedict,   json_dumps_params={'ensure_ascii': False})

