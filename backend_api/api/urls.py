from django.urls import path
from . import views


app_name = 'api'

urlpatterns = [
<<<<<<< HEAD
    path('a', views.tester),
    path('timestables/<brand>/<name>/<stime>', views.get_times_tables),

=======
    path('suns/<title>', views.suns),
    path('timestables/<brand>/<name>/<date>/<title>', views.get_times_tables),
>>>>>>> 49363d3db6adf6de684925cc2a6ed574ae1fa6d4
]