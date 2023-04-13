package com.momolearn;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.LikesRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.LikesService;
import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

@SpringBootTest
@AutoConfigureMockMvc
class MomolearnApplicationTests {
	


	
	
// 	@Autowired MembersRepository membersRepository;
// 	@Autowired MembersService membersService;
// 	@Autowired LikesRepository likesRepository;
// 	@Autowired LikesService likesService;

// 	@Autowired ApplyTeacherRepository applyTeacherRepository;
// 	@Autowired ApplyTeacherService applyTeacherService;
// 	@Autowired TeachersRepository teacherRepository;
// 	@Autowired TeachersService teacherService;

// 	@Autowired
// 	private WebApplicationContext controller;
// 	private MockHttpSession session;
// 	private MockMvc mock;
	
//	@MockBean
//	private LecturesRepository lecturesRepository;
//	@MockBean
//	private CoursesRepository coursesRepository;
//	@MockBean
//	private CategoryRepository categoryRepository;
//	@MockBean
//	private CategoryLectureRepository categoryLectureRepository;
//	@MockBean
//	private LecturesService lecturesService;
//	@MockBean
//	private TeachersService teachersService;
//	@MockBean
//	private FileService fileService;
	
// 	@BeforeEach
// 	public void init() {
// 		mock = MockMvcBuilders.webAppContextSetup(controller).build();
// 	}
	
	
//	@Test
//	void uploadCoursesTest() throws Exception {
//		
//		mock.perform(post("/lectures/upload-course")
//				.param("lectureId", "1", "1", "1")
//			    .param("title", "Title1", "Title2", "Title3")
//			    .param("time", "12:34", "13:45", "14:56")
//			    .param("url", "https://example.com/1", "https://example.com/2", "https://example.com/3"))
//				.andExpect(status().isOk())
//			    .andDo(print())
//		        .andExpect(result -> {
//		            if(result.getResolvedException() instanceof NotExistException){
//		                throw result.getResolvedException();
//		            }
//        });
//	}
//	
//	@AfterEach
//	public void clear(){
//	    session.clearAttributes();
//	    session = null;
//	}
//
//	@Test
//	@WithMockUser(username="test04", roles={"USER"})
//	void contextLoads() throws Exception {
//		
//		session = new MockHttpSession();
//		//세션값 설정
//		session.setAttribute("id", "test04");
//		
//		//로그인 테스트
//		mock.perform(post("/member/login").param("id", "test04").param("password", "1234")
//			.session(session))
//			.andExpect(status().is3xxRedirection())	//200이 아닌 304발생 - 컨트롤러에서 redirect로 화면 넘겨줄때
//			.andDo(print());
//		
//		//id와 approve(=true)로 강사 조회 테스트
//		mock.perform(get("/lectures/uploadcheck")
//			.session(session)
//			.param("id", "test04"))
//			.andExpect(status().isOk())
//			.andDo(print());
//		
//		//id와 approve(=true)로 강사 조회 테스트
//		mock.perform(multipart("/lectures/uploadlecture")
//			.param("title", "자바의 정석")
//			.param("price", "5000")
//			.param("teacher_no", "1")
//			.param("info", "강의 설명")
//			.param("description", "1"))
//			.andExpect(status().isOk())
//			.andDo(print());
//		
//	}
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
//	@Autowired
//	private WebApplicationContext controller;
//	
//	private MockHttpSession session;
//	
//	private MockMvc mock;
//	
//	@Test
//	@WithMockUser(username="test04", roles={"USER"})
//	void contextLoads() throws Exception {
//		
//		session = new MockHttpSession();
//		//세션값 설정
//		session.setAttribute("id", "test04");
//		
//		//로그인 테스트
//		mock.perform(post("/member/login").param("id", "test04").param("password", "1234")
//			.session(session))
//			.andExpect(status().is3xxRedirection())	//200이 아닌 304발생 - 컨트롤러에서 redirect로 화면 넘겨줄때
//			.andDo(print());
//		
//		//id와 approve(=true)로 강사 조회 테스트
//		mock.perform(get("/lectures/uploadcheck")
//			.session(session)
//			.param("id", "test04"))
//			.andExpect(status().isOk())
//			.andDo(print());
//		
//	}
	
	
//	@Test
//	public void update테스트() throws Exception {
//		
//		Members member = membersRepository.getById("test02");
//
//		ApplyTeacher apply = applyTeacherRepository.getById(16);
//		
//		ApplyTeacher applyDTO = new ApplyTeacher();
//		
//		applyDTO.setApplyForm(16, member, "1122", "ddddddddd", "12343", "ㅇㅇㅇㅇㅇㅇㅇㅇㅇ", apply.getApprove(), apply.getApplyRege());
//		
//		applyTeacherRepository.save(apply); // applyTeacherRepository를 사용하여 변경된 데이터 저장
//
//		//ApplyTeacher updatedApply = applyTeacherRepository.getById(16);
//		
//		assertThat(apply.getId()).isEqualTo(16);
//	}
	
}
