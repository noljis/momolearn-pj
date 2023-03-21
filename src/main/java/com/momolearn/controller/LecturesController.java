package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.service.LecturesService;
import com.momolearn.model.service.TeachersService;

import io.swagger.annotations.ApiOperation;
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
	
	//1. 강의 업로드 클릭시 유효성검사 후 강의 등록 폼으로 이동
	/* 검증
	 * 1. 현재 로그인중인지(세션id 존재하는지)
	 * 2. 현재 로그인한 유저의 등급이 강사인지 -> TeachersService에서 조회
	 * 필요한 것
	 * 1. 세션id
	 * */
	@ApiOperation(value = "강의 업로드 클릭시 유효성검사 메소드", notes = "유효성검사 후 강의 등록 폼으로 이동")
	@RequestMapping(value = "/uploadcheck", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String uploadLectureCheck(Model model, @ModelAttribute("id") String id) throws NotExistException{
		ApplyTeacherDTO applyTeacher = teachersService.getOneTeacher(id);
		
		System.out.println("결과: " + applyTeacher);
		
		model.addAttribute("teacher", applyTeacher);
		
		return "forward: /page/lecture/upload-lecture.html"; //강의 업로드 폼으로 이동
	}

	//2. 강의 업로드
	/* 검증
	 * 1. 입력안된
	 * */
	@RequestMapping(value = "/uploadlecture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String uploadLecture() {
		
		return "";
	}
	
	
	//3. 강좌 업로드
	
	//3. 강의 조회
	
	//4. 수강신청
	
	//NotExistException 관련 예외처리
	@ExceptionHandler
	public String notExistException(NotExistException ne, Model model) {
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "에러화면 이동"; //예: WEB-INF/showError.jsp
	}
	


}
