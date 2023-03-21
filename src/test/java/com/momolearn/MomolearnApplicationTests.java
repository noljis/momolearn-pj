package com.momolearn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.momolearn.model.MembersRepository;
import com.momolearn.model.service.MembersService;

@SpringBootTest
class MomolearnApplicationTests {
	@Autowired MembersRepository membersRepository;
	@Autowired MembersService membersService;

//	@Test
//	void joinTest() {
//		Members members = new Members();
////		members.setmemId("test1");
//		
//		String id = MembersService.join(members);
//		
//		Members findMem = membersRepository.findById(id).get();
//		assertThat(findMem.getMemId()).isEqualTo(members.getMemId());
//		
//	}
//	@Test
//	void TestException() {
//		Members members = new Members();
////		members.setmemId("test1");
//		
//		Members members1 = new Members();
////		members.setmemId("test1");
//		
//		MembersService.join(members);
//		IllegalStateException e = assertThrows(IllegalStateException.class, 
//				()->MembersService.join(members1));
//		
//		assertThat(e.getMessage()).isEqualTo("이미존재하는 회원입니다.");
//		
//	}
	@Autowired
	private WebApplicationContext controller;
	
	private MockHttpSession session;
	
	private MockMvc mock;
	
	@BeforeEach
	public void init() {
		mock = MockMvcBuilders.webAppContextSetup(controller).build();
	}
	
	@AfterEach
	public void clear(){
	    session.clearAttributes();
	    session = null;
	}

	@Test
	@WithMockUser(username="test04", roles={"USER"})
	void contextLoads() throws Exception {
		
		session = new MockHttpSession();
		//세션값 설정
		session.setAttribute("id", "test04");
		
		//로그인 테스트
		mock.perform(post("/member/login").param("id", "test04").param("password", "1234")
			.session(session))
			.andExpect(status().is3xxRedirection())	//200이 아닌 304발생 - 컨트롤러에서 redirect로 화면 넘겨줄때
			.andDo(print());
		
		//id와 approve(=true)로 강사 조회 테스트
		mock.perform(get("/lectures/uploadcheck")
			.session(session)
			.param("id", "test04"))
			.andExpect(status().isOk())
			.andDo(print());
		
	}

}