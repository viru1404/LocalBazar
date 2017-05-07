"""localmarket URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from .import views

urlpatterns = [
    
    url(r'^category/(?P<cat>[\w\-]+)/$',views.showproduct.as_view() , name="showproduct"),
    url(r'^suggestion/(?P<sug>[\w\-]+)/$',views.suggestion.as_view() , name="suggestion"),
    url(r'^updateproduct/$',views.updateproductview.as_view() , name="updateproductview"),
    url(r'^createproduct/$',views.createproductview.as_view() , name="createproductview"),
    url(r'^inventory/$',views.inventoryView.as_view() , name="inventoryView"),
]
#
