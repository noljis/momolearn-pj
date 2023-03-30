package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("admin")
@SessionAttributes({"members"})
@RequiredArgsConstructor
public class AdminController {
	
	private final TeachersService teachersService;
	
	private final ApplyTeacherService applyTeacherService;
	
	private final MembersService membersService;
	
	@GetMapping("/adpage")
	public String getTeacherList() {
		
		return "admin/ad-page";
	}
	

	

}
