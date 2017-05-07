from app1.models import *
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.generics import ListAPIView
from django.http import JsonResponse
from rest_framework import status
from .serializers import *


class showproduct(ListAPIView):
	serializer_class = productSerializer
	def get_queryset(self,*args,**kwargs):
		a=self.kwargs['cat']
		print(a)
		queryset_list = product.objects.filter(category=a)
		return queryset_list


class suggestion(ListAPIView):
	serializer_class = productSerializer
	def get_queryset(self,*args,**kwargs):
		a=self.kwargs['sug']
		print(a)
		queryset_list = product.objects.filter(name__icontains=a)
		#result = table.objects.filter(name__icontains=a)
		return queryset_list


class inventoryView(APIView):
	def get(self,request,format=None):
		ob1 = shop.objects.get(sid="1")
		ob = junction.objects.filter(sid=ob1)
		data = []
		for x in ob :
			obj = x.pid
			d = {'name':obj.name,'pic':obj.pic_link,'cost':x.cost,'quantity':x.quantity,'category':obj.category}
			data.append(d)
		serializer = inventorySerializer(data=data,many=True)
		if serializer.is_valid():
			return Response(serializer.data, status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)



class updateproductview(APIView):
	def post(self,request,format=None):
		ob1= product.objects.get(name=request.data.get('name'))
		ob2 = shop.objects.get(sid="1")
		ob = junction.objects.get(sid=ob2,pid=ob1)
		ob.quantity = ob.quantity - int(request.data.get('quantity'))
		ob.cost = int(request.data.get('cost'))
		ob.save()
		data = {'status':True ,'msg':"success"}
		serializer = updateproductSerializer(data=data)
		if serializer.is_valid():
			return Response(serializer.data, status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)




class createproductview(APIView):
	def post(self,request,format=None):
		pid=product.objects.all().count()
		ob = product.objects.create(pid=int(pid),name=request.data.get('name'),pic_link=request.data.get('pic'),category=request.data.get('category'))
		ob.save()
		ob2 = junction.objects.create(pid=ob, sid = shop.objects.get(sid='1'), cost=request.data.get('cost'),quantity=int(request.data.get('quantity')))
		ob2.save()
		data = {'status':True ,'msg':"success"}
		serializer = updateproductSerializer(data=data)
		if serializer.is_valid():
			return Response(serializer.data, status.HTTP_201_CREATED)
		return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
