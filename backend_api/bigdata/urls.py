from django.urls import path

from . import views

urlpatterns = [
    path('', views.create_load),
    path('result/', views.preProcess),
    path('find_si/<str:siname>', views.find_si),
]