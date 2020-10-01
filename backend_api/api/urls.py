from django.urls import path
from . import views
from bigdata import views as view

app_name = 'pyapi'

urlpatterns = [
    path('suns/<title>', views.suns),
    path('timestables/<brand>/<name>/<date>/<title>', views.get_times_tables),
    path('', view.create_load),
    path('result/', view.preProcess),
    path('find_si/<str:siname>', view.find_si),
]