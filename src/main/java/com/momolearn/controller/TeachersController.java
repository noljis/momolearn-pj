package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("teachers")
@RequiredArgsConstructor
public class TeachersController {
	
	@GetMapping(value = "/list") 
	public String getTeacherList() {
		
	    return "teachers/t-list";
	}

	//강사별 강의 리스트로 이동....?
	@GetMapping(value = "/list/{teacherNo}")
	public String getTeacherDetail(@PathVariable int teacherNo) {
		return "teachers/t-detail";
	}
}
