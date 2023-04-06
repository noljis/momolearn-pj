package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.service.MembersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("member-info")
public class MembersInfoController {
	
	private final MembersService membersService;
	
	@GetMapping
	public String getMembersInfo(String memId, Model model) throws NotExistException {
		
		MembersDTO member = membersService.getOneMember(memId);
		model.addAttribute("member", member);
		
		return "member/memberInfoView";
	}
	
	@ExceptionHandler(NotExistException.class)
	public String exHandler(NotExistException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		
		return "error";
	}
}
