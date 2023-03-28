package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("applyteacher")
@SessionAttributes({ "members", "id" })
@RequiredArgsConstructor
public class ApplyTeacherController {

	private final ApplyTeacherService applyTeacherService;

	private final MembersService membersService;

	/*
	 * 
	 * 글쓴다 - write 조회한다 - get 수정한다 - update 삭제한다 - delete 화면 - view 대조해서 검사한다 -
	 * check
	 * 
	 * 
	 * - [2] 강사 신청 현황 확인 : at-state.jsp - 강사 신청 승인 여부 확인할 수 있음
	 * 
	 * 2. 관리자 : 강사 신청 승인 및 강사 등록 - 관리자 로그인 후 강사 신청서 목록 클릭 - [1] 강사 신청서 목록 페이지 :
	 * at-list.jsp - [2] 이름 클릭시 강사 신청서 상세 보기 페이지 : at-readform.jsp 1) 강사 신청 승인 :관리자가
	 * 강사 신청서를 확인하고 승인 버튼 클릭<button id="#"> - ApplyTeacher의 approve값이 false -> true로
	 * 변경 2) 회원 등급 변경 : Members에 매핑된 해당 회원의 등급이 student -> teacher로 변경 3) 강사 정보 등록 :
	 * Teachers에 ApplyTeacher 정보 이관 (강사테이블 => 신청테이블에서 정보가 이관) 4) 강사 신청 승인이 완료된 신청서
	 * 삭제 => approve=true 인 경우 삭제? => 기능 보류
	 * 
	 */

	// O 회원 - 내 강사 신청서 목록
	@GetMapping(value = "/myapplylist")
	public String myApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("회원 : 내 신청서 목록");
		
		ApplyTeacher applyteacher = applyTeacherService.getOneApply(members.getMemId());

		if (applyteacher != null) {
			ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
			model.addAttribute("apply", apply);
		}

//		System.out.println("members 정보 : "+members);
//		model.addAttribute("member", members);

		return "teachers/at-mylist";
	}

	// O 관리자 - 강사 신청서 전체 목록
	@GetMapping(value = "/applylist")
	public String applyList(Model model) throws NotExistException {

		System.out.println("관리자 : 신청서 전체 리스트");
		
		model.addAttribute("list", applyTeacherService.getApplyList());
		
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
	public String write(Model model, @ModelAttribute("members") MembersDTO members, ApplyTeacherDTO applyDTO) throws MessageException, NotExistException {

		System.out.println("신청서 제출");
		System.out.println("-------------11 " + members.getMemId());

//		MembersDTO member = membersService.getOneMember(members.getMemId());
//		model.addAttribute("member", member);
		
		applyDTO.setMembersMemId(members.getMemId());
		ApplyTeacherDTO apply = applyTeacherService.write(applyDTO);
		System.out.println("---------------------------22");
		
		model.addAttribute("apply", apply);
		
		System.out.println("신청서 제출 정보 : " + apply);
		
		return "teachers/at-mylist";
	}
	
	
	// 강사 신청서를 applyTeacher에 저장
//	@PostMapping(value = "/write")
//	public String write(ApplyTeacherDTO applyTeacherDto) {
//	    Members members = MembersRepository.findByMemId(applyTeacherDto.getApplyId())
//	                                        .orElseThrow(() -> new NotExistException("존재하지 않는 아이디입니다."));
//	    ModelMapper modelMapper = new ModelMapper();
//	    ApplyTeacher applyTeacher = modelMapper.map(applyTeacherDto, ApplyTeacher.class);
//	    applyTeacher.setMembers(members);
//	    applyTeacherRepository.save(applyTeacher);
//	    return "redirect:/teachers/at-list";
//	}
	
	
//	@PostMapping(value = "/write")
//	public String write(Model model, @ModelAttribute("members") MembersDTO members, ApplyTeacherDTO apply) throws NotExistException {
//		
//		ApplyTeacherDTO apply = applyTeacherService.save(members.getMemId());
//		
//		System.out.println("신청서 제출");
//		
//		return "teachers/at-list";
//	}
	
	// O 회원 - 내 신청서 상세 조회
	@GetMapping(value = "/read")
	public String read(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("회원 : 신청서 상세 조회");
		
		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
		MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);
		
		return "teachers/at-readform";
	}

	// O 관리자 - 신청서 번호(id)로 상세 조회
	@GetMapping(value = "/read/{id}")
	public String read(Model model, @PathVariable int id) throws NotExistException {

		System.out.println("관리자 : 신청서 상세 조회");

		ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);
		MembersDTO member = membersService.getOneMember(apply.getMembersMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);
		
		return "teachers/at-readform";
	}

	// O 수정 페이지로 이동
	@GetMapping(value = "/updateform")
	public String updateForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		System.out.println("신청서 수정 폼 이동");
		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
		MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);

		return "teachers/at-updateform";
	}

	// X 수정
	@PostMapping(value = "/update")
	public String update(Model model, @ModelAttribute("members") MembersDTO members, ApplyTeacherDTO applyDTO) throws NotExistException {

		System.out.println("신청서 수정");
//		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
//		MembersDTO member = membersService.getOneMember(members.getMemId());
		//수정된 내용 저장 save
		
		
		ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(applyDTO.getId());
		
		System.out.println(apply);
		applyTeacherService.update(apply.getId(), apply);
		MembersDTO member = membersService.getOneMember(apply.getMembersMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);

		return "teachers/at-readform";
	}

	// O 삭제
	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) throws NotExistException {
		System.out.println("신청서 삭제");
		//ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);
		applyTeacherService.delete(id);
		return "teachers/at-mylist";
	}
	
	// O 승인
	@PostMapping(value = "/approve/{id}")
	public String approve(Model model, @PathVariable int id) throws NotExistException {

		System.out.println("신청서 승인");
		
		ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);
		
		System.out.println(apply);
		applyTeacherService.approve(id);
		MembersDTO member = membersService.getOneMember(apply.getMembersMemId());

//		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
//		MembersDTO member = membersService.getOneMember(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", member);

		return "teachers/at-readform";
	}

}
