from django.contrib import admin

# Register your models here.

from .models import product, shop, junction

admin.site.register(product)
admin.site.register(shop)
admin.site.register(junction)

