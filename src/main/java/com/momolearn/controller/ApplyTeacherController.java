package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.dto.ApplyTeacherDTOList;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;

import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("applyteacher")
@SessionAttributes({ "members" })
@RequiredArgsConstructor
public class ApplyTeacherController {

	private final ApplyTeacherService applyTeacherService;

	private final MembersService membersService;

	/*
	 * 
	 * 가입한다 - join
	 * 글쓴다 - write
	 * 조회한다 - get 
	 * 수정한다 - update
	 * 삭제한다 - delete
	 * 화면 - view
	 * 대조해서 검사한다 - check
	 * 
	 * 
	 * [1] 회원 : 강사 신청
	 * 	1. /myapplylist : 회원 로그인 후 드롭다운 메뉴 - 강사 신청 클릭 - 강사 신청 목록으로 이동 
	 * 		- test01 - 강사 신청서가 없으면 게시판 글 안뜨고 신청서 작성 버튼 활성
	 * 		- test11 - 강사 신청서가 있으면 게시판 글 뜨고 신청서 작성 버튼 비활성
	 * 	2. /writeform : 강사 신청서 작성 클릭 : 강사 신청서 작성 페이지 이동
	 * 		- 초기 상태 : 신청 폼에서는 조회한 데이터가 입력이 되어있고(ID, 이름, 메일주소)
	 * 		- 강사 신청에 필요한 정보 입력 - 연락처, 포트폴리오url, 희망분야(선택), 자기소개
	 * 	3. /write : 입력 후 제출 버튼 : ApplyTeacher에 제출 정보 저장 
	 * 		- 강사 신청 목록으로 이동
	 *  4. /read
	 *  
	 *  5. /update
	 *  
	 *  6. /delete
	 *  
	 *  

	 * 
	 * - [2] 강사 신청 현황 확인 :
	 * at-state.jsp - 강사 신청 승인 여부 확인할 수 있음
	 * 
	 * 2. 관리자 : 강사 신청 승인 및 강사 등록 - 관리자 로그인 후 강사 신청서 목록 클릭 - [1] 강사 신청서 목록 페이지 :
	 * at-list.jsp - [2] 이름 클릭시 강사 신청서 상세 보기 페이지 : at-detail.jsp 1) 강사 신청 승인 :관리자가
	 * 강사 신청서를 확인하고 승인 버튼 클릭<button id="#"> - ApplyTeacher의 approve값이 false -> true로
	 * 변경 2) 회원 등급 변경 : Members에 매핑된 해당 회원의 등급이 student -> teacher로 변경 3) 강사 정보 등록 :
	 * Teachers에 ApplyTeacher 정보 이관 (강사테이블 => 신청테이블에서 정보가 이관) 4) 강사 신청 승인이 완료된 신청서
	 * 삭제 => approve=true 인 경우 삭제? => 기능 보류
	 * 
	 */

	// 회원
	// 내 강사 신청서 목록

	@GetMapping(value = "/myapplylist")
	public String myApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacher applyteacher = applyTeacherService.getOneApply(members.getMemId());
		
		if (applyteacher != null) {
			ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId()); 
			System.out.println("apply 정보 : "+apply);
			model.addAttribute("apply", apply);
		}
		
//		System.out.println("members 정보 : "+members);
//		model.addAttribute("member", members);

		return "teachers/at-mylist";
	}

	// 관리자
	// 강사 신청 전체 목록
	@GetMapping(value = "/applylist")
	public String applyList(Model model)
			throws NotExistException {
		
//		ApplyTeacher applyteacher = applyTeacherService.getApplyList();
//		model.addAttribute("memlist", membersService.getAllMembers());
		
//		model.addAttribute("memlist", applyTeacherService.getApplyMembers());
		
		System.out.println("신청서 전체 리스트");
		model.addAttribute("list", applyTeacherService.getApplyList());		
		return "teachers/at-list";
	}

	// 강사 신청서 작성 페이지로 이동
	@GetMapping(value = "/writeform")
	public String writeForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("member", member);
		return "teachers/at-form";
	}

	// 강사 신청서 상세 조회 @PathVariable int id
	@GetMapping(value = "/read")
	public String read(Model model, @ModelAttribute("members") MembersDTO members)
			throws NotExistException {

		System.out.println("신청서 상세 조회 1");
		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
		MembersDTO member = membersService.getOneMember(members.getMemId());
		System.out.println("신청서 상세 조회 2");

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);
		return "teachers/at-readform";
	}

	// 제출
	@PostMapping(value = "/write")
	public String write(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("신청서 제출");

		return "teachers/at-list";
	}
	
	// 수정
	@PostMapping(value = "/update")
	public String update(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		
		System.out.println("신청서 수정");
		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
		MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);
		return "teachers/at-updateform";
	}

	// 삭제
	@PostMapping(value = "/{id}")
	public String delete(@PathVariable int id) throws NotExistException {
		System.out.println("신청서 삭제");
		applyTeacherService.delete(id);
		return "teachers/at-mylist";
	}

	// NotExistException 관련 예외처리
	@ExceptionHandler
	public String notExistException(NotExistException ne, Model model) {
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "에러화면 이동"; // 예: WEB-INF/showError.jsp
	}

	// MessageException 관련 예외처리
	@ExceptionHandler
	public String messageExceptio(MessageException ne, Model model) {
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "에러화면 이동"; // 예: WEB-INF/showError.jsp
	}

	// 강사 신청서를 applyTeacher에 저장
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

}
