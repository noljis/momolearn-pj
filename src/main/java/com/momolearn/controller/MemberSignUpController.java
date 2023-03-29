package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.model.service.MembersService;

@RestController
@SessionAttributes({"members"})
public class MemberSignUpController {
	
	@Autowired
	private MembersService membersService;

	//아이디 중복 체크 (성공)
	@PostMapping("member/checkOk")
	public boolean dedupId( String memId) throws Exception {
		
		boolean check = membersService.checkId(memId);
		
		return check;
	}			
}