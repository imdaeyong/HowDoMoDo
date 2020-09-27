from django.db import models

class TheatersUrls(models.Model):
    brand = models.CharField(max_length=50)
    name = models.CharField(max_length=100)
    url = models.CharField(max_length=255)
    class Meta:
        db_table = 'theaters_urls'

    def __str__(self):
        return "{} {}".format(self.brand, self.name)