package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes({"id"})
@RequestMapping("teachers")
public class TeachersController {
	
	@Autowired
	private TeachersService teachersService;
	
	@Autowired
	private MembersService membersService;
	
	
/* 1. 강사 등록하기
 * 	- 회원 로그인 -> main.html -> 드롭다운 메뉴(My Pages) -> 강사 신청 클릭
 * 	- 컨트롤러에서 세션 ID -> 신청폼으로 넘어감
 * 
 * 2. 강사 신청 테이블
 * 	- 신청 폼에서는 조회한 데이터가 입력이 되어있고(ID, 이름, 메일주소)
 * 	- 강사 신청에 필요한 정보는 입력한다.
 * 	- 필요 속성: 포트폴리오url, 희망분야(선택), 자기소개
 * 
 * 3. 강사 테이블
 * 	- 관리자가 강사 신청서를 확인하고 승인 버튼 클릭<button id="#apply"> : 관리자가 접속했을때 버튼 나오도록
 * 	- [1] 해당 회원 테이블의 등급이 강사로 변경
 * 	- [2] 강사 테이블 생성(강사테이블 => 신청테이블에서 정보가 이관)
 * 	- [3] 강사 신청 테이블 삭제 => 기능 보류
 */
	
	//회원
	//강사 신청폼으로 이동 : 세션 id 넘기기
	@RequestMapping(value = "/applyform", method = RequestMethod.POST)
	public String applyform(Model sessionData, @RequestParam("id") String id) {
		
		return "redirect:/teachers/teacherInsertForm.html";
	}

	
	
	//관리자
	//강사 신청 확인 페이지로 이동
	@RequestMapping(value = "/applyapproveform", method = RequestMethod.GET)
	public String applyapproveform() {
		
		return "redirect:/teachers/teacherApproveForm.html";
	}
	

}
