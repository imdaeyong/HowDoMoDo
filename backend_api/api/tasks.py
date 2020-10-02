from __future__ import absolute_import, unicode_literals
import random
# from celery.decorators import task
from django.utils import timezone
import time
from celery import shared_task

# @task(name="sum_two_numbers")
@shared_task(name="sum_two_numbers")
def add(x, y):
    return x + y


# @task(name="multiply_two_numbers")
@shared_task(name="multiply_two_numbers")
def mul(x, y):
    total = x * (y * random.randint(3, 100))
    return total


# @task(name="sum_list_numbers")
@shared_task(name="sum_list_numbers")
def xsum(numbers):
    return sum(numbers)

@shared_task()
def asynchronous_test():
    time_start = timezone.now();
    time.sleep(30)
    time_end = timezone.now()
    time_gap = time_end - time_start
    return time_gap
