package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		
		return "main";
		
	}
	
	
	@GetMapping("/about")
	public String about() {
		
		return "about";
		
	}
	

}
