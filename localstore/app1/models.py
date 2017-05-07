from django.db import models

# Create your models here.

class product(models.Model):
	pid = models.CharField(max_length =255)
	name = models.CharField(max_length =255)
	pic_link = models.CharField(max_length =255)
	category = models.CharField(max_length =255)

	def __str__(self):
		return self.name

class shop(models.Model):
	sid  = models.CharField(max_length =255)
	name = models.CharField(max_length =255)
	typeofstuff = models.CharField(max_length =255)
	address = models.CharField(max_length = 255)
	lattitude = models.FloatField(null=True, blank=True, default=None)
	longitude = models.FloatField(null = True , blank = True , default = None)
	ishomedelivery = models.BooleanField(default=True)
	isopen = models.BooleanField(default = True)
	openingtime = models.TimeField(blank=True, null=True, default = None)
	closingtime =models.TimeField(blank=True, null=True, default = None)

	def __str__(self):
		return self.name

class junction(models.Model):
	pid =models.ForeignKey(product, on_delete=models.CASCADE)
	sid = models.ForeignKey(shop, on_delete=models.CASCADE)
	cost = models.CharField(max_length =255)
	quantity = models.IntegerField(default =0)
	def __str__(self):
		return self.pid.name

class junction1(models.Model):
	pname =models.CharField(max_length=255)
	sname =models.CharField(max_length=255)
	cost = models.CharField(max_length =255)
	quantity = models.IntegerField(default =0)
	def __str__(self):
		return self.pname

class message(models.Model):
	status = models.BooleanField(default=True)
	msg = models.CharField(max_length=255)

	def __str__(self):
		return message.msg
