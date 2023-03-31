package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		
		return "main"; // WEB-INF/main.jsp
	}
	
	
	@GetMapping("/about")
	public String about() {
		
		return "about"; // WEB-INF/about.jsp
	}
	

}
