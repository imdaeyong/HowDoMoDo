
from django.http import HttpResponse, JsonResponse
from . import recommand_activity
from . import npmodel
import time
import requests
from bs4 import BeautifulSoup

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
    # df = recommand.find_si(siname)
    # storedict = recommand_activity.Recommand.df_to_dict(df)
    storedict = recommand.result_dict[siname]
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(storedict, json_dumps_params={'ensure_ascii': False})


def np_init(request):
    np = npmodel.npmodel
    np.init()
    test = {"message": "ok"}
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True})

def review_list(request, code):
    pagelist = [1, 2]
    review_ls = []
    for page in pagelist:
        url = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=" + code + "&type=after&page=" + str(page)
        resp = requests.get(url)
        html = BeautifulSoup(resp.content, 'html.parser')

        score_result = html.find('div', {'class': 'score_result'})
        lis = score_result.findAll('li')

        for idx, li in enumerate(lis):
            review_text = li.find('p').getText()
            review_text = review_text.replace('\n', '')
            review_text = review_text.replace('\t', '')
            review_text = review_text.replace('\r', '')
            review_text = review_text.replace('관람객', '')
            if (review_text != ''):
                review_ls.append(review_text)

    np = npmodel.npmodel
    np_result = []
    for review in review_ls:
        rs = np.test_sentences(review)
        np_result.append(rs)

    total = len(np_result)
    p = np_result.count(1)
    n = np_result.count(0)

    result = {"ps": round(p/float(total), 1), "ns": round(n/float(total), 1)}
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True})

