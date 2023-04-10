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
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("applyteacher")
@SessionAttributes({ "members" })
@RequiredArgsConstructor
public class ApplyTeacherController {

	private final ApplyTeacherService applyTeacherService;
	
	private final MembersService membersService;
	
	private final TeachersService teachersService;


	@GetMapping(value = "/myapplylist")
	public String getMyApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacher applyTeacher = applyTeacherService.getOneApply(members.getMemId());

		if (applyTeacher != null) {
			
			ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
			model.addAttribute("apply", apply);
			
		}

		return "teachers/at-mylist";
	}

	@GetMapping(value = "/applylist")
	public String getApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		if(members.getGrade().equals("admin")) {
			
			model.addAttribute("list", applyTeacherService.getApplyList());
		}
		
		return "teachers/at-list";

	}

	@GetMapping(value = "/writeform")
	public String writeForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		MembersDTO member = membersService.getOneMember(members.getMemId());
		model.addAttribute("member", member);

		return "teachers/at-form";
	}

	@PostMapping(value = "/write")
	public String writeApplyForm(Model model, @ModelAttribute("members") MembersDTO members, ApplyTeacherDTO apply)
			throws MessageException, NotExistException {

		apply.setMembersMemId(members.getMemId());
		ApplyTeacherDTO newApply = applyTeacherService.write(apply);

		model.addAttribute("apply", newApply);

		return "teachers/at-mylist";
	}

	@GetMapping(value = "/read")
	public String read(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-readform";
	}

	@GetMapping(value = "/read/{id}")
	public String read(Model model, @PathVariable int id, @ModelAttribute("members") MembersDTO members)
			throws NotExistException, MessageException {

		if(members.getGrade().equals("admin")) {
			
			ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);
			MembersDTO member = membersService.getOneMember(apply.getMembersMemId());
			
			model.addAttribute("apply", apply);
			model.addAttribute("member", member);
		}

		return "teachers/at-readform";
	}

	@GetMapping(value = "/updateform")
	public String updateForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-updateform";
	}

	@PostMapping(value = "/update/{id}")
	public String update(@PathVariable int id, Model model, @ModelAttribute("members") MembersDTO members,
			ApplyTeacherDTO apply) throws NotExistException {

		applyTeacherService.update(id, apply);
		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-readform";
	}

	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) throws NotExistException {
		
		applyTeacherService.delete(id);
		return "teachers/at-mylist";
	}

	@PostMapping(value = "/approve/{id}")
	public String approve(Model model, @PathVariable int id) throws NotExistException, MessageException {

		applyTeacherService.approve(id);
		ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(id);

		membersService.updateGrade(apply.getMembersMemId());
		MembersDTO member = membersService.getOneMember(apply.getMembersMemId());
		
		teachersService.saveOneTeacher(apply);
		model.addAttribute("apply", apply);
		model.addAttribute("member", member);

		return "teachers/at-readform";
	}
	
	@ExceptionHandler(value = NotExistException.class)
	public String notExistException(NotExistException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		
		return "error";
	}

}
