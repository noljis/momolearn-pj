package com.momolearn.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.momolearn.model.MembersRepository;
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.FileService;
import com.momolearn.model.service.MembersService;

@RestController
@SessionAttributes({"members"})
public class MemberSignUpController {
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private MembersRepository membersRepository;
	
	@Autowired
	private FileService fileService;
    
//	// 회원가입 후 정보 보기 
//	@PostMapping(value = "member/join", produces = "application/json; charset=UTF-8")
//	public String memInsert(Model sessionData, Members members, @RequestParam("memId") String memId
//						,@RequestParam("password") String pw, @RequestParam("name") String name, 
//						@RequestParam("email") String email,
//						@RequestParam("file") MultipartFile file) throws SQLException, IOException {
//		
//        // profile 파일 저장
//		if(file == null) {
//			members.setProfile("user.jpg");
//		}else {
//			String savedFileName = fileService.getProfile(memId, file);
//			members.setProfile(savedFileName);
//			
//		}
//        
//		members.setMemId(memId);
//		members.setEmail(email);
//		members.setGrade("student");
//		members.setName(name);
//		members.setPw(pw);
//        members.setRegdate(LocalDateTime.now());
//		
//		membersService.memJoin(members);
//		
//		sessionData.addAttribute("members", members); // 회원가입 정보를 모델에 담아서 리턴
//
//		return "member/joinInfo"; //String으로 처리돼서 화면이 넘어감...
//	}
//	
	//아이디 중복 체크 (성공)
	@PostMapping("member/checkOk")
	public boolean dedupId( String memId) throws Exception {
		
		boolean check = membersService.checkId(memId);
		
		return check;
	}			
}