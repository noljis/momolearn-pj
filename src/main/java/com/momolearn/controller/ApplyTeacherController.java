package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

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
	private final TeachersService teachersService;

	/*
	 * 
	 * 글쓴다 - write 조회한다 - get 수정한다 - update 삭제한다 - delete 화면 - view 대조해서 검사한다 -
	 * check
	 * 
	 * 
	 * 1. 회원 : 강사 신청서를 작성, 수정, 삭제할 수 있다 강사 신청서는 1명당 1개만 작성할 수 있다. 강사 신청서가 승인되면 수정과
	 * 삭제를 할 수 없다. => 어차피 teacher로 등급 바뀌면 메뉴가 보이지 않기때문에 등급 변경 기능이 우선 - 신청서 작성 - [0]
	 * 회원 로그인 후 드롭다운 메뉴에서 강사 신청 클릭 -> 내 강사 신청서 목록 페이지 : at-mylist.jsp - [1] 강사 신청서가
	 * 없는 경우 강사 신청서 작성 버튼 활성화가 되어있다. 강사 신청서 작성 버튼 클릭 - [2] 신청에 필요한 정보를 기입한 수 제출한다.
	 * 
	 * - 신청서 승인 상태에서는 불가능 - 신청서 수정 - [0] 내 강사 신청서에서 -> 수정 버튼 클릭 - [1] 수정할 정보를 기입한 수
	 * 수정하여 제출한다.
	 * 
	 * - 신청서 삭제 - [0] 내 강사 신청서에서 -> 삭제 버튼 클릭
	 * 
	 * 2. 관리자 : 강사 신청서를 읽고 승인을 할 수 있다. 강사 신청서 승인 버튼을 누르면 등급이 강사로 변경되고 강사로 등록이 된다.
	 * 
	 * - 신청서 승인 + 등급 변경 + 강사 등록 - [0] 관리자 로그인 후 드롭다운 메뉴에서 강사 신청서 목록 클릭 -> 강사 신청서 목록
	 * 페이지 : at-list.jsp - [1] 이름 클릭시 강사 신청서 상세 보기 페이지 : at-readform.jsp - [2] 강사 신청
	 * 승인 :관리자가 강사 신청서를 확인하고 승인 버튼 클릭 - 1) ApplyTeacher의 approve값이 false -> true로 변경
	 * - 2) 회원 등급 변경 : Members에 매핑된 해당 회원의 등급이 student -> teacher로 변경 - 3) 강사 정보 등록
	 * : Teachers에 ApplyTeacher 정보 이관 (강사테이블 => 신청테이블에서 정보가 이관) - 4) 강사 신청 승인이 완료된
	 * 신청서 삭제 => approve=true 인 경우 삭제? => 기능 보류
	 * 
	 */

	// O 회원 - 내 강사 신청서 목록
	@GetMapping(value = "/myapplylist")
	public String getMyApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("회원 : 내 신청서 목록" + members);

		ApplyTeacher applyTeacher = applyTeacherService.getOneApply(members.getMemId());

		if (applyTeacher != null) {
			ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
			model.addAttribute("apply", apply);
		}

		return "teachers/at-mylist";
	}

	// O 관리자 - 강사 신청서 전체 목록
	@GetMapping(value = "/applylist")
	public String getApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		if(members.getGrade().equals("admin")) {
			System.out.println("관리자 : 신청서 전체 리스트" + members);
			model.addAttribute("list", applyTeacherService.getApplyList());
		}
		return "teachers/at-list";

	}

	// O 작성 페이지로 이동
	@GetMapping(value = "/writeform")
	public String writeForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		System.out.println("신청서 작성 페이지");

		MembersDTO member = membersService.getOneMember(members.getMemId());
		model.addAttribute("member", member);

		System.out.println("신청서를 작성하는 회원 정보 : " + member);

		return "teachers/at-form";
	}

	// O 작성
	@PostMapping(value = "/write")
	public String writeApplyForm(Model model, @ModelAttribute("members") MembersDTO members, ApplyTeacherDTO apply)
			throws MessageException, NotExistException {

		System.out.println("신청서 제출");
		System.out.println("-------------11 " + members.getMemId());

		apply.setMembersMemId(members.getMemId());
		ApplyTeacherDTO newApply = applyTeacherService.write(apply);
		System.out.println("---------------------------22");

		model.addAttribute("apply", newApply);

		System.out.println("신청서 제출 정보 : " + newApply); // approve = null

		return "teachers/at-mylist";
	}

	// O 회원 - 내 신청서 상세 조회
	@GetMapping(value = "/read")
	public String read(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("회원 : 신청서 상세 조회");
		
		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
		//MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		System.out.println("신청서 조회 정보 : " + apply);
		return "teachers/at-readform";
	}

	// O 관리자 - 신청서 번호(id)로 상세 조회
	@GetMapping(value = "/read/{id}")
	public String read(Model model, @PathVariable int id, @ModelAttribute("members") MembersDTO members)
			throws NotExistException, MessageException {

		if(members.getGrade().equals("admin")) {
			System.out.println("관리자 : 신청서 상세 조회");
			
			ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);
			MembersDTO member = membersService.getOneMember(apply.getMembersMemId());
			
			model.addAttribute("apply", apply);
			model.addAttribute("member", member);
		}

		return "teachers/at-readform";
	}

	// O 수정 페이지로 이동
	@GetMapping(value = "/updateform")
	public String updateForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("신청서 수정 폼 이동");
		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
		//MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-updateform";
	}

	// O 수정
	@PostMapping(value = "/update/{id}")
	public String update(@PathVariable int id, Model model, @ModelAttribute("members") MembersDTO members,
			ApplyTeacherDTO apply) throws NotExistException {

		System.out.println("신청서 수정");

		applyTeacherService.update(id, apply);
		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-readform";
	}

	// O 삭제
	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) throws NotExistException {
		System.out.println("신청서 삭제");
		applyTeacherService.delete(id);
		return "teachers/at-mylist";
	}

	// O 승인
	@PostMapping(value = "/approve/{id}")
	public String approve(Model model, @PathVariable int id) throws NotExistException, MessageException {
		System.out.println("신청서 승인");
		applyTeacherService.approve(id);
		ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);
		System.out.println("승인 후 신청서 정보" + apply);

		// MembersDTO member = membersService.getOneMember(apply.getMembersMemId());

		MembersDTO member = membersService.updateGrade(apply.getMembersMemId());
		System.out.println("등급 변경 후 회원 정보" + member);
		System.out.println("apply 정보 : " + apply);
		
		TeachersDTO teacher = teachersService.saveOneTeacher(apply);
		System.out.println("teacher 저장 : " + teacher);
		model.addAttribute("apply", apply);
		model.addAttribute("member", member);

		return "teachers/at-readform";
	}

}
