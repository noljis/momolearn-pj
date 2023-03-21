package com.momolearn.controller;


import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.FileService;
import com.momolearn.model.service.MembersService;
import com.momolearn.util.DBUtil;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
@SessionAttributes({"id"})
public class MembersController {
	
	@Autowired
	private MembersService membersService;

	
	@Autowired
	private FileService fileService;
	
    /**
     * 회원가입 
     */
	// 회원가입 폼
    @GetMapping("/joinView")
    protected ModelAndView memJoinView() throws SQLException {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("page/auth/join");   
		return mv;
	}
    
    //회원가입 진행
	@PostMapping("/join")
//	public String memJoin(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("email") String email, 
//							@RequestParam("name") String name, @RequestParam("profile") MultipartFile file) throws IOException{
//		//사진 디렉토리에 저장: 반환타입 String[유저id.확장자]
//		String profile = fileService.getProfile(id, file);
//		
//		//?회원가입 메소드
//	    
//		//?저장되고 이동할 경로
//	    return ""; 
//	}
	public Members memJoin(Members members , @RequestParam("id") String id, @RequestParam("profile") MultipartFile file) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			
			//사진 디렉토리에 저장: 반환타입 String[유저id.확장자]
			String profile = fileService.getProfile(id, file);
			
			tx.begin();
			em.persist(members);
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			em.close();
		}
		
        return members;
	}

	
	// 아이디 중복 체크 확인
	// http://localhost/team2_studyroom/StdMembers/checkOk?id=값
	@RequestMapping(value = "/checkOk", method = RequestMethod.GET)
	protected String idCheck(String id) throws Exception {

		String check = "아이디 중복입니다.";

		boolean result = membersService.duplecateID(id);

		if (result == true) {
			check = "사용가능한 아이디 입니다.";
		}
		System.out.println("checkOk() -----" + result);

		return check;
	}
	// 아이디 중복 체크 확인
	@PostMapping(value = "dedupId")
	public boolean dedupId(@RequestParam(value = "mem_id") String memIdCheck) throws Exception {
		boolean check = membersService.checkId(memIdCheck);
	
		return check;
	}
	//
	@PostMapping(value = "/validateUser")
	public boolean validateUser(@RequestParam(value = "id") String memId, @RequestParam(value = "pw") String memPw) throws Exception {
		boolean check = membersService.validateUser(memId, memPw);
		
		return check;
	}
    /**
     * 로그인 
     */
//	@RequestMapping(value = "/login", method=RequestMethod.POST)
//	public String login(Model sessionData, @RequestParam("id") String id, @RequestParam("password") String password) throws SQLException {
//		
//		boolean validate = membersService.loginMember(id, password);
//		System.out.println("----"+validate);
//		
//		if(validate == true) { //로그인성공
//			System.out.println("id확인 " + id);
//			( sessionData).addAttribute("id", id);  //세션에 데이터  저장
//			
//			
//			return "redirect:/main.jsp"; //로그인 후 메인화면
//		}else {
//			return "redirect:/loginError.jsp"; //에러메시지 창 띄우는걸로 수정하기	
//		}
//	}
	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(Model sessionData, @RequestParam("memId") String memId, @RequestParam("password") String password) throws Exception {
		MembersDTO membersDTO = membersService.loginMember(memId, password);
		System.out.println("----" + membersDTO);
		sessionData.addAttribute("memId", memId); // 세션에 데이터 저장
		
		return "redirect:/page/main.html";
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
