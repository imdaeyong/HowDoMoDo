from .cgv import run as run_cgv
from .lotte import run as run_lotte
from .mega import run as run_mega
import re


def get_theater_timestables(brand, url, title, date):
    brand = brand.lower()
    if brand == 'lotte': # lotte run
        regex = re.compile(r'.*detailDivisionCode=([0-9]*)&cinemaID=([0-9]*)')
        matchobj = regex.search(url)
        detaildivisioncode = matchobj.group(1)
        cinemaid = matchobj.group(2)
        result = run_lotte(title=title, detailDivisionCode=detaildivisioncode, cinemaID=cinemaid, date=date)

    elif brand == 'cgv': # cgv run
        regex = re.compile(r'.*areacode=([0-9A-Z\%]*)\&theaterCode=([0-9]*)')
        matchobj = regex.search(url)
        areacode = matchobj.group(1)
        theatercode = matchobj.group(2)
        result = run_cgv(title=title, areacode=areacode, theatercode=theatercode, date=date)

    elif brand == 'mega': # mega run
        regex = re.compile(r'.*brchNo=([0-9]*)')
        matchobj = regex.search(url)
        brcno = matchobj.group(1)
        result = run_mega(title=title, brchNo=brcno, date=date)

    if len(result) == 0: # fail
        return {'status':400, 'timestables':None}
    else : # success
        return {'status':200,'timestables':result}