package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("teachers")
@RequiredArgsConstructor
public class TeachersController {
	
	private final TeachersService teachersService;
	private final ApplyTeacherService applyTeacherService;
	private final MembersService membersService;
	
	
	// #TODO
	// X 강사 전체 리스트 
	@GetMapping(value = "/list" , produces = "application/json; charset=UTF-8")
	public String getTeacherList(Model model) {
		
		model.addAttribute("list", teachersService.getTeacherList());
		
		return "teachers/t-list";
	}
	
	// #TODO
	// X 선택한 1명의 강사 프로필
	@GetMapping(value = "/{id}" , produces = "application/json; charset=UTF-8")
	public String teacher(Model model, @PathVariable int id) throws NotExistException {
		
		TeachersDTO teacher = teachersService.getOneTeacher(id);
		String tname = teachersService.getOneteacher(id);
		model.addAttribute("teacher", teacher);
		
		return "teachers/t-detail";
	}
	
	// #TODO
	// X 선택한 1명의 강사 강의 목록 -> json 
	@GetMapping(value = "/lec/{id}" , produces = "application/json; charset=UTF-8")
	public String teaLec(@PathVariable int id) {
		
		return "";
	}
}
