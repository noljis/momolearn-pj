package com.momolearn.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.momolearn.model.service.FileService;
import com.momolearn.model.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/join")
	public String register(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("email") String email, 
							@RequestParam("name") String name, @RequestParam("profile") MultipartFile file) throws IOException{
		//사진 디렉토리에 저장: 반환타입 String[유저id.확장자]
		String profile = fileService.getProfile(id, file);
		
		//?회원가입 메소드
	    
		//?저장되고 이동할 경로
	    return "";
	}
}
