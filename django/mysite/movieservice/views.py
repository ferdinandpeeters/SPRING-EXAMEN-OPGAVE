# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render

# Create your views here.

from django.http import HttpResponse

import redis

def index(request):
    return HttpResponse("Hello, world. You're at the polls index.")
	
def listmovies(request):
	html = "<h1>List Movies</h1><ul>";
	r = redis.StrictRedis(host='localhost', port=6379, db=0)
	keys = r.keys(pattern='mWT4*')
	for key in keys:
		html += "<li>" + key[4:] +"</li>"
	html += "<br><a href='/movieservice/actors'>Zoek acteurs</a>\r\n"
	html += "</ul>";
	return HttpResponse(html)
	
def actors(request):
	html = "<h1>Search actors</h1><ul>";
	r = redis.StrictRedis(host='localhost', port=6379, db=0)
	keys = r.keys(pattern='mWT4*')
	movie = "mWT4" + request.GET.get('movie')
	
	if (movie is not None):
		if movie in keys:
			html += "<ul>"
			while(r.llen(movie)!=0):
				html += "<li>" + r.rpop(movie) + "</li>"
			html += "</ul>"
	r.flushdb()
	r.rpush('mWT4The Godfather', 'Al Pacino')
	r.rpush('mWT4The Godfather', 'Marlon Brando')
	r.rpush('mWT4The Godfather', 'Robert Duvall')
	r.rpush("mWT4Schindler's List", 'Liam Neeson')
	r.rpush("mWT4Schindler's List", 'Ralph Fiennes')
	r.rpush("mWT4Schindler's List", 'Ben Kingsley')
	r.rpush('mWT4Saving Private', 'Ryan Tom Hanks')
	r.rpush('mWT4Saving Private', 'Matt Damon')
	r.rpush('mWT4Saving Private', 'Vin Diesel')
	r.rpush('mWT4Back to the Future', 'Michael J. Fox')
	r.rpush('mWT4Back to the Future', 'Christopher Lloyd')
	r.rpush('mWT4Back to the Future', 'Lea Thompson')
	r.rpush('mWT4Casablanca', 'Ingrid Bergman')
	r.rpush('mWT4Casablanca', 'Humphrey Bogart')
	r.rpush('mWT4Casablanca', 'Peter Lorre')
	r.rpush('mWT4The Big Lebowski', 'Julianne Moore')
	r.rpush('mWT4The Big Lebowski', 'Jeff Bridges')
	r.rpush('mWT4The Big Lebowski', 'Tara Reid')
	
	html += "<form method=GET action='/movieservice/actors'>\r\n" 
	html += "<div class='form-group row'>\r\n" 
	html += "			<div class='col-xs-4'>\r\n" 
	html += "				<label for='Movie'>Movie: </label>\r\n" 
	html += "		    		<input type='text' class='form-control' name='movie' id='movie'>\r\n" 
	html += "		    	</div>\r\n" 
	html += "	    	</div>\r\n" 
	html += "	    	\r\n" 
	html += "		<input type=SUBMIT value='Submit'>\r\n" 
	html += "</form>\r\n"  
	return HttpResponse(html)