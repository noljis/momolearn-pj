package com.momolearn.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.momolearn.model.entity.Members;
import com.momolearn.model.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
@SessionAttributes({"id"})
public class MembersSignInController {
	
	@Autowired
	private MembersService membersService;
	

	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(Model sessionData, @RequestParam("memId") String memId, @RequestParam("password") String password) throws Exception {
		Members members = membersService.loginMember(memId, password);
		System.out.println("----" + members);
		sessionData.addAttribute("memId", memId); // 세션에 데이터 저장
		sessionData.addAttribute("password", password); // 세션에 데이터 저장
		
		return "redirect:/page/main.html";
	}
	
	// 로그인 정보 확인
	@PostMapping(value = "/validateUser")
	public boolean validateUser(@RequestParam(value = "memId") String memId, @RequestParam(value = "password") String memPw) throws Exception {
		boolean check = membersService.validateUser(memId, memPw);
		
		return check;
	}
	
	//로그아웃
	@GetMapping(value = "/sessionOut")
	public ModelAndView sessionOut(SessionStatus status) throws Exception {

		status.setComplete();
		status = null;
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/member/refresh");

		return mv;
	}
	
	@GetMapping(value = "/refresh")
	public ModelAndView refresh() throws Exception { 
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/index.html");
		
		return mv;
	}
	
    /**
     * 멤버 정보 관리 기능 
     */
	//로그인 후 정보조회
	//http://localhost/team2_studyroom/WEB-INF/auth/viewOne.jsp
	@RequestMapping(value = "/viewOne2", method = RequestMethod.GET)
	public ModelAndView viewOne(@ModelAttribute("id") String id) throws SQLException {
		ModelAndView mv = new ModelAndView();
//		StudyMembers members = memdao.getMember(id);
//		System.out.println(members);
//		mv.addObject("allData", members);
		mv.setViewName("auth/viewOne2");

		return mv;
	}
		
	//프로필 수정 페이지 이동
	@RequestMapping(value = "/updatepage", method = RequestMethod.GET)
	public ModelAndView updatePage(String id) throws SQLException {

		ModelAndView mv = new ModelAndView();

//		mv.addObject("allData", mem.getMember(id));
		mv.setViewName("auth/update");

		return mv;
	}
	
	//프로필 수정 기능
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("id") String id, @RequestParam("nickname") String nickname, @RequestParam("email") String email, @RequestParam("password") String pw, @RequestParam("goal") String goal) throws SQLException {

		System.out.println("update() ----- " + id);
		
//		memdao.memUpdate(id, email, goal, nickname, pw);

		return "auth/updateSuccess";
	}
	
	//회원 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String deleteId) throws SQLException {
		
//		memdao.delete(deleteId);
		
		return "auth/deleteSuccess"; 
	}
	
	//관리자 화면으로 이동
	@RequestMapping(value="/adPage", method = RequestMethod.GET)
	public String adMain() {
		return "auth/adPage";
	}
	
	//관리자 - 전체회원조회
	//http://localhost:8080/team2_studyroom/StdMembers/allView
	//spring 코드로 변환 url - allView 
	@RequestMapping(value="/adAllView", method = RequestMethod.GET)
	public ModelAndView getMembers() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
			
//		mv.addObject("allData", memdao.getMembers());
		mv.setViewName("auth/adAllView");
		
		return mv;
	}
	
	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	//http://localhost/team2_studyroom/WEB-INF/auth/error.jsp
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) { 
		System.out.println("예외 처리 전담");
		e.printStackTrace();

		req.setAttribute("errorMsg", e.getMessage());

		return "forward:/error.jsp";
	}

	
}
