package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.momolearn.model.service.MembersService;

@RestController
public class MemberSignUpController {
	@Autowired
	private MembersService membersService;
	
	@PostMapping("member/checkOk")
	public boolean dedupId(String id) throws Exception {
		System.out.println("------- " + id);
		//boolean check = membersService.checkId(id);
//System.out.println(check);
		return true;
	}			
}
