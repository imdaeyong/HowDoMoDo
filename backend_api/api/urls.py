from django.urls import path
from . import views
from bigdata import views as view

app_name = 'api'

urlpatterns = [
    path('a', views.tester),
    path('timestables/<brand>/<name>/<stime>', views.get_times_tables),
    path('', view.create_load),
    path('result/', view.preProcess),
    path('find_si/<str:siname>', view.find_si),
]