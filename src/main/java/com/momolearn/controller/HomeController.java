package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping(produces = "application/json;charset=UTF-8")
	public String home() {
		
		return "main"; // WEB-INF/main.jsp
	}
	
	@GetMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login() {
		
		return "member/login"; // WEB-INF/member/login.jsp
	}
	
	@GetMapping(value = "/join", produces = "application/json;charset=UTF-8")
	public String join() {
		
		return "member/join"; // WEB-INF/member/login.jsp
	}
	

}
