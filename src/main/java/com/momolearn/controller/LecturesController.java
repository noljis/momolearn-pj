package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.service.LecturesService;
import com.momolearn.model.service.TeachersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("lectures")
@SessionAttributes({ "id" })
public class LecturesController {
	
	@Autowired
	private LecturesService lecturesService;
	
	@Autowired
	private TeachersService teachersService;
	
	//1. 강의 업로드 클릭시 유효성검사
	/* 검증
	 * 1. 현재 로그인중인지(세션id 존재하는지)
	 * 2. 현재 로그인한 유저의 등급이 강사인지 -> Teacher에서 조회
	 * 필요한 것
	 * 1. 세션id
	 * */
	@RequestMapping(value = "/uploadcheck", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String uploadLectureCheck(Model model, @ModelAttribute("id") String id) throws NotExistException{
		
		teachersService.getOneTeacher(id);
		
		return "redirect:/comm/write.jsp";
	}
	
	//2. 강의 업로드
	/* 검증
	 * 1. 입력안된
	 * */
	@RequestMapping(value = "/uploadform", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String uploadLecture() {
		return "redirect:/comm/write.jsp";
	}
	
	
	//3. 강좌 업로드
	
	//3. 강의 조회
	
	//4. 수강신청
	

}
