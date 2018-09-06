package edu.ap.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import edu.ap.spring.jpa.Joke;
import edu.ap.spring.jpa.JokeRepository;

@Controller
@Scope("session")
public class JokeController {
   
	   @Autowired
	   JokeRepository repository;
	   
   public JokeController() {
   }
       
   @RequestMapping("/joke")
   @ResponseBody
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
		   		"<h1>Random Joke</h1>\r\n" + 
		   		"<br/>\r\n" + 
		   		"<p>Personalized random Joke</p>" +
		   		"\r\n" + 
		   		"<form method=POST action='joke_post' onsubmit='return validate()'>\r\n" + 
		   		"		<div class='form-group row'>\r\n" + 
		   		"		 	<div class='col-xs-4'>\r\n" + 
		   		"				<label for='firstName'>First name: </label>\r\n" + 
		   		"		    		<input type='text' class='form-control' name='firstName' id='firstName'>\r\n" + 
		   		"	    		</div>\r\n" + 
		   		"	    	</div>\r\n" + 
		   		"		<div class='form-group row'>\r\n" + 
		   		"			<div class='col-xs-4'>\r\n" + 
		   		"				<label for='lastName'>Last name: </label>\r\n" + 
		   		"		    		<input type='text' class='form-control' name='lastName' id='lastName'>\r\n" + 
		   		"		    	</div>\r\n" +  
		   		"		<input type=SUBMIT value='Submit'>\r\n" + 
		   		"</form>\r\n" + 
		   		"\r\n" + 
		   		"<br/><br/>\r\n" +
		   		"</div>\r\n" + 
		   		"\r\n" + 
		   		"</body>\r\n" + 
		   		"</html>";
		   return html;
   }
		   
   @PostMapping("/joke_post")
   @ResponseBody
   public String joke_post(
		   @RequestParam(value = "firstName") String firstName, 
		   @RequestParam("lastName") String lastName) throws Exception {
	   
	   String[] apiJoke = getJoke(firstName, lastName);
	   Joke joke = new Joke(Integer.parseInt(apiJoke[0]),apiJoke[1]);
	   boolean exists = true;
	   if (repository.findByJoke(joke.getJoke()).isEmpty())
	   {
		   exists = false;
		   repository.save(joke);
	   }
	   String html = "<html>\r\n" + 
		   		"<head>\r\n" + 
		   		"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n" + 
		   		"\r\n" + 
		   		"<link rel='stylesheet' href='/resources/css/bootstrap.min.css'>\r\n" + 
		   		"<script type='text/javascript' src='/resources/js/app.js'></script>\r\n" + 
		   		"\r\n" + 
		   		"<title>Post Joke</title>\r\n" + 
		   		"</head>\r\n" + 
		   		"\r\n" + 
		   		"<body>\r\n" + 
		   		"\r\n" + 
		   		"<div class='well'>\r\n" + 
		   		"<h1>Post Joke</h1>\r\n" + 
		   		"<br/>\r\n" + 
		   		"\r\n" + 
		   		"<p>Joke:</p>" +
	   			"\r\n" + 
		   		joke.getJoke() + 
		   		"<br/><br/>\r\n";
	   		if (exists) 
	   		{
	   			html += "<p>Joke already exists in database</p>";
	   		}
	   		else 
	   		{
	   			html += "<p>Joke doesn't exists in database. Saved in Database!</p>";
	   		}
	   		html+=
	   			"\r\n" +
		   		"<a href='/joke'>Opnieuw</a>\r\n" + 
		   		"</div>\r\n" + 
		   		"\r\n" + 
		   		"</body>\r\n" + 
		   		"</html>";
		   return html;
   }
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }
   
   public static String[] getJoke(String firstName, String lastName) throws Exception {
		String url = "http://api.icndb.com/jokes/random?firstName=" + firstName + "&lastName=" + lastName;
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        // optional default is GET
	        con.setRequestMethod("GET");
	        //add request header
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        int responseCode = con.getResponseCode();
	        System.out.println("\nSending 'GET' request to URL : " + url);
	        System.out.println("Response Code : " + responseCode);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	        	response.append(inputLine);
	        }
	        in.close();
	        
	        //print in String
	        System.out.println(response.toString());
	        
	        String[] s = response.toString().split("\"");
	        String[] st = {s[8].substring(2,s[8].length()-2),s[11]};
	        return st;
		    }
}
