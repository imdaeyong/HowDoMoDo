from django.shortcuts import render

# Create your views here.

from django.http import HttpResponse, JsonResponse
from . import recommand_activity


def index(request):

    # 정적 메소드 및 클래스 변수 확인
    # print(recommand_activity.Recommand.testnum)
    # recommand_activity.Recommand.test()
    # print(recommand_activity.Recommand.testnum)
    recommand_activity.Recommand.create()
    recommand_activity.Recommand.load_csv()

    test = {"A": [1, 2], "B": [3, 4]}
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})

def result(request):

    print(recommand_activity.Recommand.preprocessing(recommand_activity.Recommand.df_list))
    test = {"A": [1, 2], "B": [3, 4]}
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})
