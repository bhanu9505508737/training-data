package com.cg.app.helloWorldClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "result")
	@RequestMapping("/")
	public String readingList(Model model) {

		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8090/recommended", String.class);
		String message = entity.getBody();
		System.out.println(message);
		model.addAttribute("message", message);
		return "hello";
	}

	
	  public String result() 
	  { 
		  return "not running "; 
	   }
	 

}
