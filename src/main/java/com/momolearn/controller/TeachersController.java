package com.momolearn.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;
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
	
	
/* 1. 회원 : 강사 신청
 * 	- 회원 로그인 후 드롭다운 메뉴(My Pages) -> 강사 신청 클릭
 * 	- [1] 강사 신청페이지 : at-form.jsp 
 * 			1) 초기 상태 : 컨트롤러에서 세션 id로 id, name, email 조회
 *		 		- 신청 폼에서는 조회한 데이터가 입력이 되어있고(ID, 이름, 메일주소)
 * 	 		2) 강사 신청에 필요한 정보 입력
 * 				- 입력이 필요한 속성 : 연락처, 포트폴리오url, 희망분야(선택), 자기소개
 * 			3) 입력 후 제출 버튼 : ApplyTeacher에 제출 정보 저장
 * 	- [2] 강사 신청 현황 확인 : at-state.jsp
 * 		- 강사 신청 승인 여부 확인할 수 있음
 * 
 * 2. 관리자 : 강사 신청 승인 및 강사 등록
 * 	- 관리자 로그인 후 강사 신청서 목록 클릭
 * 	- [1] 강사 신청서 목록 페이지 : at-list.jsp
 * 	- [2] 이름 클릭시 강사 신청서 상세 보기 페이지 : at-detail.jsp
 * 			1) 강사 신청 승인 :관리자가 강사 신청서를 확인하고 승인 버튼 클릭<button id="#">
 * 		 		- ApplyTeacher의 approve값이 false -> true로 변경
 * 			2) 회원 등급 변경 : Members에 매핑된 해당 회원의 등급이 student -> teacher로 변경
 * 			3) 강사 정보 등록 : Teachers에 ApplyTeacher 정보 이관 (강사테이블 => 신청테이블에서 정보가 이관)
 * 			4) 강사 신청 승인이 완료된 신청서 삭제 => approve=true 인 경우 삭제? => 기능 보류
 * 
 */
	
	//회원
	//강사 신청폼으로 이동 : 세션 id 넘기기
	@RequestMapping(value = "/applyform", method = RequestMethod.POST)
	public String applyform(Model sessionData, @RequestParam("id") String id) {
		
		return "redirect:/teachers/at-form.jsp";
	}
	
	
	//강사 신청서를 applyTeacher에 저장
//	@PostMapping("/applysave")
//	public String applyTeacher(ApplyTeacherDTO applyTeacherDto) {
//	    Members members = MembersRepository.findByMemId(applyTeacherDto.getApplyId())
//	                                        .orElseThrow(() -> new NotExistException("존재하지 않는 아이디입니다."));
//	    ModelMapper modelMapper = new ModelMapper();
//	    ApplyTeacher applyTeacher = modelMapper.map(applyTeacherDto, ApplyTeacher.class);
//	    applyTeacher.setMembers(members);
//	    applyTeacherRepository.save(applyTeacher);
//	    return "redirect:/";
//	}
//	@RequestMapping(value = "/members/{memId}", method = RequestMethod.GET)
//	public String getMember(@PathVariable String memId, Model model) {
//	    Members member = MembersRepository.findById(memId).orElse(null);
//	    model.addAttribute("member", member);
//	    return "member";
//	}

	
	
	//관리자
	//강사 신청서 목록 페이지로 이동
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public String applyapproveform() {
		
		return "redirect:/teachers/at-list.jsp";
	}
	

}
