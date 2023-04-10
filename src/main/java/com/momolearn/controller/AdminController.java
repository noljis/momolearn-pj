package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("admin")
@SessionAttributes({"members"})
@RequiredArgsConstructor
public class AdminController {
	
	@GetMapping("/adpage")
	public String getTeacherList() {
		
		return "admin/ad-page";
	}
	

	

}
