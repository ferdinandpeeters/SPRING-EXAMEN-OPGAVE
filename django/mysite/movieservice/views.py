# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render

# Create your views here.

from django.http import HttpResponse

import redis

def index(request):
    return HttpResponse("Hello, world. You're at the polls index.")
	
def listmovies(request):
	r = redis.StrictRedis(host='localhost', port=6379, db=0)
	x = r.get('foo')
	return HttpResponse(x)