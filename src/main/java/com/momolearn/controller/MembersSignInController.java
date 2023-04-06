package com.momolearn.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@SessionAttributes({"members"})
@RequiredArgsConstructor
public class MembersSignInController {
	
	private final MembersService membersService;
	
	private final FileService fileService;
	
	private final KakaoService kakaoService;
	
	@GetMapping(value = "/kakaoLogin")
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpSession session, Model model) throws SQLException, MessageException {

		ModelAndView mv = new ModelAndView();
		
		String access_token = kakaoService.getAccessToken(code); 
		
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_token);

		if(userInfo.get("email") != null) {
			
	        String email = userInfo.get("email").toString();
	        String[] memId = email.split("@");
	        String name = userInfo.get("nickname").toString();
	        String password = "1111" ;
	        
	        MembersDTO memberDto = new MembersDTO(memId[0], password, name, email, "user.jpg", "student", LocalDateTime.now());
	        MembersDTO res = membersService.memJoin(memberDto);
	        
	        if (res != null) {
	        	
	            session.setAttribute("memId", email);
	            session.setAttribute("access_token", access_token);
	            mv.addObject("memId", email);
	            
	    		MembersDTO members = membersService.loginMember(memId[0].toString(), password);
	    		
	    		if (members != null) { 
	    			
	    			model.addAttribute("member", members); 

	    			mv.setViewName("member/joinInfo");

	    		} else {
	    			
	    			mv.setViewName("Error");
	    		}
	            
			}else {
				
	            throw new RuntimeException("회원가입에 실패하였습니다.");
	        }
		
		}
		return mv;
	}
	
    @GetMapping(value = "/joinView", produces = "application/json; charset=UTF-8")
    protected String memJoinView() throws SQLException {
		
		return "member/join";
	}
    
	@PostMapping(value = "/join", produces = "application/json; charset=UTF-8")
	public String memInsert(Model model, MembersDTO members, @RequestParam("memId") String memId
						,@RequestParam("password") String pw, @RequestParam("name") String name, 
						@RequestParam("email") String email,
						@RequestParam("file") MultipartFile file) throws SQLException, IOException, MessageException {
		
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
    
	@GetMapping(value = "/loginView", produces = "application/json; charset=UTF-8")
    protected String memLoginView() throws SQLException {
		
		return "member/login";
	}
    
	@GetMapping(value = "/findIdView")
	public String findIdForm() {
		
		return "member/findId";
	}

	@PostMapping(value = "/findId", produces = "application/json; charset=UTF-8")
	public String findId(Model model, @RequestParam("email") String email ) throws SQLException {
		
		MembersDTO member = membersService.findId(email);
		
        if (member == null) {
        	
            model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
            
        } else {
        	
            model.addAttribute("member", member);
        }
        
        return "member/findIdResult";
	}
	
	@GetMapping(value = "/findPwdView", produces = "application/json; charset=UTF-8")
	public String findPwdForm() {
		
		return "member/findPw";
	}

	@PostMapping(value = "/findPwd", produces = "application/json; charset=UTF-8")
	public String findPwd(Model model, @RequestParam("memId") String memId, @RequestParam("email") String email) throws SQLException {
	
		MembersDTO member = membersService.findPw(memId,email);
		
        if (member == null) {
        	
            model.addAttribute("msg", "일치하는 회원 정보가 없습니다.");
            
        } else {
            model.addAttribute("member", member);
            
        }
        
        return "member/findPwResult";
	
	}
    
	@PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
	public String login(Model model, @RequestParam("memId") String memId, 
						@RequestParam("password") String password) throws Exception {
		
		MembersDTO members = membersService.loginMember(memId, password);
		
		if (members != null) { 
			model.addAttribute("members", members); 

			return "redirect:/"; 

		} else {
			
			return "loginError"; 
		}
		
	}
	
	@GetMapping(value = "/sessionOut", produces = "application/json; charset=UTF-8")
	public String sessionOut(SessionStatus status, HttpSession session) throws Exception {

		status.setComplete();
		status = null;
		
	    String accessToken = (String) session.getAttribute("access_token");

	    if (accessToken != null) {
	    	
	        kakaoService.kakaoLogout(accessToken);
	        session.removeAttribute("access_token");
	        session.removeAttribute("userId");
	    }
		
		return "redirect:/";
	} 
	
	@GetMapping(value = "/myinfo", produces = "application/json; charset=UTF-8")
	public String viewOne(Model model, @ModelAttribute("members") MembersDTO mem) throws SQLException {
 
		return "member/myinfoview";
	}
	
	@GetMapping(value = "/updatepage", produces = "application/json; charset=UTF-8")
	public String updatePage(Model model, @ModelAttribute("members") MembersDTO mem) throws SQLException {

		return "member/updateInfo";
	}
		
	@PostMapping(value = "/update", produces = "application/json; charset=UTF-8")
	public String updatePage( Model model, HttpSession session, @ModelAttribute("members") MembersDTO mem,
			@RequestParam("newpw") String newpw, @RequestParam("password") String password, 
			@RequestParam("name") String name, 
			@RequestParam("file") MultipartFile file) throws SQLException, IOException {
		
		String memId = mem.getMemId(); 
		
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
		
		model.addAttribute("members", mem); 
		
		return "member/myinfoview"; 
		
	}
	
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
	
	@ExceptionHandler
	public String totalEx(SQLException e, HttpServletRequest req) { 
		
		e.printStackTrace();
		req.setAttribute("errorMsg", e.getMessage());

		return "error";
	}

	
}