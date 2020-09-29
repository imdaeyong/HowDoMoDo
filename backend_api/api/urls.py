from django.urls import path
from . import views

app_name = 'api'

urlpatterns = [
    path('timestables/<brand>/<name>/<date>/<title>', views.get_times_tables),
]