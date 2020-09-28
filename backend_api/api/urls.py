from django.urls import path
from . import views

app_name = 'api'

urlpatterns = [
    path('a', views.tester),
    path('timestables/<brand>/<name>/<stime>', views.get_times_tables),
]