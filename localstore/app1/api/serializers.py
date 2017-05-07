from rest_framework.serializers import ModelSerializer
from rest_framework import serializers
from app1.models import *

class productSerializer(serializers.ModelSerializer):
	class Meta:
		model = product
		fields = [
		'id',
		'pid',
		'name',
		'pic_link',
		'category',
		]


class updateproductSerializer(serializers.ModelSerializer):
	class Meta:
		model = message
		fields = [
		'status',
		'msg',
		]

class shopSerializer(serializers.ModelSerializer):
	class Meta:
		model = shop
		fields = [
		'sid',
		'name',
		'address',
		'lattitude',
		'longitude',
		'ishomedelivery',
		'isopen',
		]

class inventorySerializer(serializers.Serializer):
	name = serializers.CharField(max_length =255)
	quantity = serializers.IntegerField()
	cost =serializers.IntegerField()
	pic = serializers.CharField(max_length=255)
	category = serializers.CharField(max_length=255)
