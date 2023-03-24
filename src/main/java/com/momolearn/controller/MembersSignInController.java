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

import com.momolearn.model.entity.Members;
import com.momolearn.model.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
@SessionAttributes({"members"})
public class MembersSignInController {
	
	@Autowired
	private MembersService membersService;
	
	//회원가입 입력폼
    @GetMapping("/joinView")
    protected String memJoinView() throws SQLException {
		
		return "member/join";
	}
    
	//로그인 입력폼 (확인)
    @GetMapping("/loginView")
    protected String memLoginView() throws SQLException {
		
		return "member/login";
	}
    
	// id 찾기 페이지 이동 (확인)
	@RequestMapping(value = "/findIdView", method = RequestMethod.GET)
	public String findIdForm() {
		
		return "member/findId";
	}

	// id 찾기  (확인)
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findId(Model model, @RequestParam("email") String email ) throws SQLException {
		
		
		Members member = membersService.findId(email);
		
        if (member == null) {
        	
            model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
            
        } else {
        	
            model.addAttribute("member", member);
        }
        
        return "forward:/WEB-INF/member/findIdResult.jsp"; // 이동할 JSP 파일명
	}
	
	// pwd 찾기 페이지 이동 (확인)
	@RequestMapping(value = "/findPwdView", method = RequestMethod.GET)
	public String findPwdForm() {
		
		return "member/findPw";
	}

	// pwd 찾기 (확인)
	@RequestMapping(value = "/findPwd", method = RequestMethod.POST)
	public String findPwd(Model model, @RequestParam("memId") String memId, @RequestParam("email") String email) throws SQLException {
	
		Members member = membersService.findPw(memId,email);
		
        if (member == null) {
            model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
            
        } else {
            model.addAttribute("member", member);
            
        }
        
        return "forward:/WEB-INF/member/findPwResult.jsp"; // 이동할 JSP 파일명
	
	}
    
	//로그인 (확인)
	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(Model sessionData, @RequestParam("memId") String memId, 
						@RequestParam("password") String password) throws Exception {
		
		Members members = membersService.loginMember(memId, password);
		
		if (members != null) { // 로그인성공
			sessionData.addAttribute("members", members); // 세션에 프로필 저장

			return "forward:/WEB-INF/main.jsp"; // 로그인 후 메인화면

			
		} else {
			
			return "loginError"; // 에러메시지 창 띄우는걸로 수정하기
		}
		
		
	}
	
	//로그아웃 (확인)
	@GetMapping(value = "/sessionOut")
	public String sessionOut(SessionStatus status) throws Exception {

		status.setComplete();
		status = null;
		
		return "redirect:/member/refresh";
	}
	
	@GetMapping(value = "/refresh")
	public String refresh() throws Exception {
		
		return "redirect:/"; // HomeController의 home() 메소드로 이동
	}
	
    /**
     * 멤버 정보 관리 기능 
     */
	//로그인 후 정보조회 (확인)
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String viewOne(Model sessionData, @ModelAttribute("members") Members mem) throws SQLException {
		
		return "forward:/WEB-INF/member/myinfo.jsp";
	}
		
	//프로필 수정 페이지 이동 (확인)
	@RequestMapping(value = "/updatepage", method = RequestMethod.GET)
	public String updatePage(Model sessionData, @ModelAttribute("members") Members mem) throws SQLException {

		return "forward:/WEB-INF/member/updateInfo.jsp";
	}
	
	//프로필 수정 기능 (미확인)
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("members") Members updatedMember, Model model) throws SQLException {

		membersService.updateMember(updatedMember);

		return "auth/updateSuccess";
	}
	
	//회원 삭제 (미확인)
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public String delete(@RequestParam("memId") String deleteId) throws SQLException {
//		
//		membersService.deleteMember(deleteId);
//		
//		return "redirect:/index.html"; 
//	}
//	
//	//관리자 화면으로 이동
//	@RequestMapping(value="/adPage", method = RequestMethod.GET)
//	public String adMain() {
//		return "member/adPage";
//	}
	
	//관리자 - 전체회원조회
//	@RequestMapping(value="/adAllView", method = RequestMethod.GET)
//	public ModelAndView getMembers() throws SQLException {
//		
//		ModelAndView mv = new ModelAndView();
//			
////		mv.addObject("allData", memdao.getMembers());
//		mv.setViewName("member/adAllView");
//		
//		return mv;
//	}
	
	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	//http://localhost/team2_studyroom/WEB-INF/auth/error.jsp
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) { 
		e.printStackTrace();

		req.setAttribute("errorMsg", e.getMessage());

		return "error";
	}

	
}
