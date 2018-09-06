package edu.ap.spring.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("session")
public class JokeController {
   
   public JokeController() {
   }
       
   @RequestMapping("/joke")
   public String joke() {
	   String html = "<html>\r\n" + 
		   		"<head>\r\n" + 
		   		"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n" + 
		   		"\r\n" + 
		   		"<link rel='stylesheet' href='/resources/css/bootstrap.min.css'>\r\n" + 
		   		"<script type='text/javascript' src='/resources/js/app.js'></script>\r\n" + 
		   		"\r\n" + 
		   		"<title>Joke</title>\r\n" + 
		   		"</head>\r\n" + 
		   		"\r\n" + 
		   		"<body>\r\n" + 
		   		"\r\n" + 
		   		"<div class='well'>\r\n" + 
		   		"<h1>Inhaal examen</h1>\r\n" + 
		   		"<br/>\r\n" + 
		   		"<form method=POST action='new' onsubmit='return validate()'>\r\n" + 
		   		"		<div class='form-group row'>\r\n" + 
		   		"		 	<div class='col-xs-4'>\r\n" + 
		   		"				<label for='firstName'>Your name : </label>\r\n" + 
		   		"		    		<input type='text' class='form-control' name='firstName' id='firstName'>\r\n" + 
		   		"	    		</div>\r\n" + 
		   		"	    	</div>\r\n" + 
		   		"		<div class='form-group row'>\r\n" + 
		   		"			<div class='col-xs-4'>\r\n" + 
		   		"				<label for='lastName'>Course: </label>\r\n" + 
		   		"		    		<input type='text' class='form-control' name='lastName' id='lastName'>\r\n" + 
		   		"		    	</div>\r\n" +  
		   		"		<input type=SUBMIT value='Sumbit'>\r\n" + 
		   		"</form>\r\n" + 
		   		"\r\n" + 
		   		"<br/><br/>\r\n" +
		   		"</div>\r\n" + 
		   		"\r\n" + 
		   		"</body>\r\n" + 
		   		"</html>";
		   return html;
   }
		   
   @RequestMapping("/joke_post")
   public String joke_post() {
	   return "";
   }
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }
}
