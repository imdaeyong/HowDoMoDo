from django.urls import path
from . import views

app_name = 'api'

urlpatterns = [
    path('test/', views.test1),
    path('cgv/', views.test2),
    path('a/', views.a),
]