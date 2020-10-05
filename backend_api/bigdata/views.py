
from django.http import HttpResponse, JsonResponse
from . import recommand_activity
from . import npmodel
import time

from rest_framework import status
import requests
from bs4 import BeautifulSoup


def create_load(request):

    # 정적 메소드 및 클래스 변수 확인

    recommand = recommand_activity.Recommand()
    print(recommand)
    recommand.create()
    recommand.load_csv()
    result = {"message": "스파크 세션 연결 완료"}
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True}, status=status.HTTP_200_OK)

def preProcess(request):
    start = time.time()
    recommand = recommand_activity.Recommand()
    recommand.preprocessing()
    print(recommand)
    recommand.preAnalysis('강릉시')
    result = {"message": "전처리 완료"}
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True}, status=status.HTTP_200_OK)

def preAnalysis(request):
    start = time.time()
    #si_list = ['강릉시', '동해시', '속초시', '원주시', '인제군', '춘천시', '고양시', '광명시', '광주시', '구리시', '군포시', '김포시', '남양주시', '부천시', '성남시', '수원시', '시흥시', '안산시', '안성시', '안양시', '양주시', '오산시', '용인시', '의정부시', '이천시', '파주시', '평택시', '포천시', '하남시', '화성시', '거제시', '거창군', '김해시', '사천시', '양산시', '진주시', '창원시', '통영시', '경산시', '경주시', '구미시', '김천시', '문경시', '상주시', '안동시', '영주시', '예천군', '포항시', '광산구', '동구', '북구', '서구', '달서구', '달성군', '동구', '북구', '서구', '수성구', '중구', '동구', '서구', '유성구', '중구', '금정구', '기장군', '남구', '동래구', '부산진구', '북구', '사상구', '사하구', '연제구', '중구', '진구', '해운대구', '강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구', '세종시', '남구', '북구', '중구', '계양구', '남구', '남동구', '부평구', '서구', '연수구', '중구', '광양시', '나주시', '목포시', '순천시', '여수시', '군산시', '남원시', '익산시', '전주시', '정읍시', '서귀포시', '제주시', '공주시', '논산시', '당진시', '보령시', '서산시', '아산시', '천안시', '홍성군', '음성군', '제천시', '진천군', '청주시', '충주시']
    si_list = ['강릉시', '동해시', '속초시', '원주시', '인제군', '춘천시', '고양시', '광명시', '광주시', '구리시', '군포시']
    recommand = recommand_activity.Recommand()
    print(recommand)

    for si in si_list:
        recommand.preAnalysis(si)
    result = {"message": "사전 분석 완료"}
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True}, status=status.HTTP_200_OK)

def find_si(request, siname):
    print(siname)
    start = time.time()
    recommand = recommand_activity.Recommand()
    print(recommand)
    # df = recommand.find_si(siname)
    # storedict = recommand_activity.Recommand.df_to_dict(df)
    storedict = recommand.result_dict[siname]
    print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간
    return JsonResponse(storedict,  json_dumps_params={'ensure_ascii': False}, status=status.HTTP_200_OK)



def np_init(request):
    np = npmodel.npmodel
    np.init()
    test = {"message": "ok"}
    return JsonResponse(test, json_dumps_params={'ensure_ascii': True}, status=status.HTTP_200_OK)

def review_list(request, code):
    title_code = {'뮬란' : '167491', '에이바' : '195377', '테넷':'190010', '아웃포스트':'195450', '그린랜드':'195983', '극장판 포켓몬스터: 뮤츠의 역습 EVOLUTION':'187305', '고스트 오브 워':'195425'}
    titlecode = title_code[code]
    pagelist = [1, 2]
    review_lls =[]
    for page in pagelist:
        url = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=" + titlecode + "&type=after&page=" + str(page)
        resp = requests.get(url)
        html = BeautifulSoup(resp.content, 'html.parser')

        score_result = html.find('div', {'class': 'score_result'})
        lis = score_result.findAll('li')

        for idx, li in enumerate(lis):
            review_ls = []
            review_text = li.find('p').getText()
            review_text = review_text.replace('\n', '')
            review_text = review_text.replace('\t', '')
            review_text = review_text.replace('\r', '')
            review_text = review_text.replace('관람객', '')
            if (review_text != ''):
                review_ls.append(review_text)
                review_lls.append(review_ls)
    np = npmodel.npmodel
    np_result = []
    for review in review_lls:
        rs = np.test_sentences(review)
        np_result.append(rs)

    total = len(np_result)
    p = np_result.count(1)
    n = np_result.count(0)
    print(p)
    print(n)
    print(total)
    print(np_result)
    result = {"ps": round(p/float(total), 2), "ns": round(n/float(total), 2)}
    return JsonResponse(result, json_dumps_params={'ensure_ascii': True}, status=status.HTTP_200_OK)


