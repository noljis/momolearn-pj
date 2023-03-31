package com.momolearn.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.momolearn.exception.MessageException;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.service.FileService;
import com.momolearn.model.service.KakaoService;
import com.momolearn.model.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
@SessionAttributes({"members"})
public class MembersSignInController {
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private KakaoService kakaoService;
	
	
	@GetMapping(value = "/kakaoLogin")
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		System.out.println(1);
		// 인증코드 요청 전달
		String access_token = kakaoService.getAccessToken(code); 
		System.out.println(2);
		// 인증코드 토큰 전달
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_token); //여기 메소드에서 에러남
		System.out.println(3);
		if(userInfo.get("email") != null) {
			System.out.println(4);
			session.setAttribute("userId", userInfo.get("email"));
			System.out.println(5);
			session.setAttribute("access_token", access_token);
			System.out.println(6);
		}
		
		mv.addObject("userId", userInfo.get("email"));
		mv.setViewName("main");
		
		return mv;
	}
	
	//카카오 로그아웃
//	@RequestMapping(value = "/kakaoLogout")
//	public ModelAndView kakaoLogout(@RequestParam("code") String code, HttpSession session) {
//		
//		ModelAndView mv = new ModelAndView();
//		
//		kakaoService.kakaoLogout((String) session.getAttribute("access_token"));
//		session.removeAttribute("access_token");
//		session.removeAttribute("userId");
//		mv.setViewName("member/kakaoLogin");
//		
//		return mv;
//	}
	
	//회원가입 입력폼
    @GetMapping(value = "/joinView", produces = "application/json; charset=UTF-8")
    protected String memJoinView() throws SQLException {
		
		return "member/join";
	}
    
	// 회원가입 후 정보 보기 
	@PostMapping(value = "/join", produces = "application/json; charset=UTF-8")
	public String memInsert(Model model, MembersDTO members, @RequestParam("memId") String memId
						,@RequestParam("password") String pw, @RequestParam("name") String name, 
						@RequestParam("email") String email,
						@RequestParam("file") MultipartFile file) throws SQLException, IOException, MessageException {
		
        // profile 파일 저장
		if(file != null && !file.isEmpty()) {
			
			String savedFileName = fileService.getProfile(memId, file);
			members.setProfile(savedFileName);
			
		}else {
			
			members.setProfile("user.jpg");
		}
        
		members.setMemId(memId);
		members.setEmail(email);
		members.setGrade("student");
		members.setName(name);
		members.setPw(pw);
        members.setRegdate(LocalDateTime.now());
		
		membersService.memJoin(members); 
		
		model.addAttribute("member", members); 

		return "member/joinInfo"; 
	}
    
	//로그인 입력폼 (확인)
	@GetMapping(value = "/loginView", produces = "application/json; charset=UTF-8")
    protected String memLoginView() throws SQLException {
		
		return "member/login";
	}
    
	// id 찾기 페이지 이동 (확인)
	@GetMapping(value = "/findIdView")
	public String findIdForm() {
		
		return "member/findId";
	}

	// id 찾기  (확인)
	@PostMapping(value = "/findId", produces = "application/json; charset=UTF-8")
	public String findId(Model model, @RequestParam("email") String email ) throws SQLException {
		
		
		MembersDTO member = membersService.findId(email);
		
        if (member == null) {
        	
            model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
            
        } else {
        	
            model.addAttribute("member", member);
        }
        
        return "member/findIdResult"; // 이동할 JSP 파일명
	}
	
	// pwd 찾기 페이지 이동 (확인)
	@GetMapping(value = "/findPwdView", produces = "application/json; charset=UTF-8")
	public String findPwdForm() {
		
		return "member/findPw";
	}

	// pwd 찾기 (확인)
	@PostMapping(value = "/findPwd", produces = "application/json; charset=UTF-8")
	public String findPwd(Model model, @RequestParam("memId") String memId, @RequestParam("email") String email) throws SQLException {
	
		MembersDTO member = membersService.findPw(memId,email);
		
        if (member == null) {
            model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
            
            
        } else {
            model.addAttribute("member", member);
            
        }
        
        return "member/findPwResult"; // 이동할 JSP 파일명
	
	}
    
	//로그인 (확인)
	@PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
	public String login(Model model, @RequestParam("memId") String memId, 
						@RequestParam("password") String password) throws Exception {
		
		MembersDTO members = membersService.loginMember(memId, password);
		
		if (members != null) { // 로그인성공
			model.addAttribute("members", members); // 세션에 프로필 저장

			return "redirect:/"; // 로그인 후 메인화면

		} else {
			
			return "loginError"; 
		}
		
	}
	
	//로그아웃 (확인)
	@GetMapping(value = "/sessionOut", produces = "application/json; charset=UTF-8")
	public String sessionOut(SessionStatus status) throws Exception {

		status.setComplete();
		status = null;
		
		return "redirect:/";
	}
	
    /**
     * 멤버 정보 관리 기능 
     */
	//로그인 후 정보조회 (확인)
	@GetMapping(value = "/myinfo", produces = "application/json; charset=UTF-8")
	public String viewOne(Model model, @ModelAttribute("members") MembersDTO mem) throws SQLException {
 
		return "member/myinfoview";
	}
	
	//프로필 수정 페이지 이동 (확인)
	@GetMapping(value = "/updatepage", produces = "application/json; charset=UTF-8")
	public String updatePage(Model model, @ModelAttribute("members") MembersDTO mem) throws SQLException {

		return "member/updateInfo";
	}
		
	//프로필 수정 기능 (확인)
	@PostMapping(value = "/update", produces = "application/json; charset=UTF-8")
	public String updatePage( Model model, HttpSession session, @ModelAttribute("members") MembersDTO mem,
			@RequestParam("newpw") String newpw, @RequestParam("password") String password, 
			@RequestParam("name") String name, 
			@RequestParam("file") MultipartFile file) throws SQLException, IOException {
		
		// 세션에서 아이디 불러오기
		String memId = mem.getMemId(); // 세션에서 아이디 불러오기
		
		// profile 파일 저장 -- 수정
		if(file == null || file.isEmpty()) {
			mem.setProfile(memId+".jpg");
			
		}else {
			String savedFileName = fileService.getProfile(memId, file);
			mem.setProfile(savedFileName);
		}
		
		mem.setName(name);
		
		if(newpw==null  || newpw.isEmpty()) {
			mem.setPw(mem.getPw());
			
		}else {
			mem.setPw(newpw);
			
		}
		
		membersService.updateMember(mem);
		
		model.addAttribute("members", mem); // 수정 정보를 모델에 담아서 리턴
		
		return "member/myinfoview"; 
		
	}
	
	//회원 삭제 (확인)
	@GetMapping(value = "/delete/{memId}", produces = "application/json; charset=UTF-8")
	public String delete(Model model, @PathVariable String memId, SessionStatus status){
		 try {
		        membersService.deleteMember(memId);
				status.setComplete();
				status = null;
		        model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        model.addAttribute("message", "회원 탈퇴에 실패했습니다.");
		        return "member/myinfoview";
		    }
		 
		 return "redirect:/";
	}

	//전체회원조회

	
	// 예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	//http://localhost/team2_studyroom/WEB-INF/auth/error.jsp
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) { 
		e.printStackTrace();

		req.setAttribute("errorMsg", e.getMessage());

		return "error";
	}

	
}