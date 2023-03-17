package com.momolearn.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	
	//이미지 파일을 로컬에 회원id로 저장 -> 이미지 이름을 DTO의 image컬럼에 추가 -> Service로 보내기
	@PostMapping("/imagedown")
	public String register(@ModelAttribute MembersDTO membersDTO,
	                       @RequestParam("profile") MultipartFile file) {
	    String fileName = file.getOriginalFilename(); // 파일 이름 가져오기
	    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1); // 파일 확장자명 가져오기
	    String savedFileName = /*membersDTO.getMemId()+ */"." + fileExtension; // 저장될 파일 이름 설정

	    try {
	        // 로컬 저장소에 파일 저장하기
	        String savePath = "저장할 경로";
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get(savePath + savedFileName);
	        Files.write(path, bytes);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    membersService.joinMember();
	    
	    return "";
	}

}
