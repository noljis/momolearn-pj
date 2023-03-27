package com.momolearn.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.CategoryDTO;
import com.momolearn.model.dto.CoursesDTO;
import com.momolearn.model.dto.CoursesListDTO;
import com.momolearn.model.dto.LectureCoursesDTO;
import com.momolearn.model.dto.LecturesDTO;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.dto.TeacherMemberDTO;
import com.momolearn.model.service.FileService;
import com.momolearn.model.service.LecturesService;
import com.momolearn.model.service.TeachersService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("lectures")
@SessionAttributes({"members"})
@RequiredArgsConstructor
public class LecturesController {
	
	private final LecturesService lecturesService;
	
	private final TeachersService teachersService;
	
	private final FileService fileService;

	// 1. 강의 업로드 클릭시 유효성검사 후 강의 등록 폼으로 이동(완료)
	@ApiOperation(value = "강의 업로드 클릭시 유효성검사 메소드", notes = "유효성검사 후 강의 등록 폼으로 이동")
	@GetMapping(value = "/upload-check", produces = "application/json;charset=UTF-8")
	public String checkUploadLecture(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		log.info(members + "로 유효성 검사 컨트롤러");
		// 강사 찾기 teacher로 찾아야함
		TeacherMemberDTO teacher = teachersService.getOneTeachers(members.getMemId());
		if(teacher != null) {
		
			model.addAttribute("teacher", teacher);
		
		}

		return "lecture/lecture-form"; // 강의 업로드 폼으로 이동
	}

	
	// 2. 강의 업로드(완료)
	/*
	 * 1. html에 작성된 속성 강의명(title), 강사번호(teacher_no), 사진(MultipartFile), 가격(price),
	 * 한줄설명(info), 상세설명(description) 2. id(autoincrement), cnt: default=0(강좌 등록할때마다
	 * 하나씩 update), regdate(@CreatedDate), applyCnt: default=0(학생이 수강신청 할때마다 하나씩
	 * update) 3. 이미지 저장 후 lectureDTO.setImage에 이름.확장자명으로 set 4. 카테고리, 카테고리-강좌 테이블에
	 * 카테고리 저장 5. 강의 등록 후 강좌 등록 폼으로 이동
	 */
	@ApiOperation(value = "강의 업로드 메소드", notes = "강의 등록 후 강의에 속하는 강좌 등록 화면으로 이동")
	@PostMapping(value = "/upload-lecture", produces = "application/json;charset=UTF-8")
	public String uploadLecture(Model model, @ModelAttribute LecturesDTO lectureDTO,
			@RequestParam("file") MultipartFile file, @RequestParam("category") String category)
			throws MessageException, IOException {
		
		log.info("강의 업로드 메소드: " + lectureDTO.getTitle() + category);
		// 썸네일 먼저 저장 -> 이름.확장자명으로 반환해서 setImage 대입
		if(file == null) {
			lectureDTO.setImage("default.jpg");
		}else {
			String thumbnail = fileService.getLectureImage(lectureDTO.getTitle(), file);
			lectureDTO.setImage(thumbnail);
		}

		// 강의 등록
		LecturesDTO lecture = lecturesService.uploadLecture(lectureDTO);
		System.out.println(lecture.getTitle());	

		//카테고리, 카테고리-강좌 저장
		lecturesService.getCategory(category, lecture);
		//강의 model에 저장
		model.addAttribute("lecture", lecture);

		return "redirect:courses-form/" + URLEncoder.encode(lecture.getTitle(), StandardCharsets.UTF_8) + "/" + lecture.getId(); // 강좌 업로드 폼으로 이동
	}
	
	//강좌 폼으로 이동
	@GetMapping(value = "/courses-form/{title}/{id}", produces = "application/json;charset=UTF-8")
	public String courseForm(Model model, @PathVariable String title, @PathVariable int id) {
		log.info("강좌 폼으로 이동: " + title + id);
		model.addAttribute("lectitle", title);
		model.addAttribute("id", id);
		
		return "lecture/course-form";
	}
	
	
	/* 강좌 여러개 다중 저장 saveAll
	 * */
	@ApiOperation(value = "강좌 업로드 메소드", notes = "특정 강의에 해당하는 강좌들을 등록")
	@PostMapping(value = "/upload-course", produces = "application/json;charset=UTF-8")
	public String uploadCourse(Model model, @RequestBody CoursesListDTO coursesListDTO)throws NotExistException {
		
		log.info("강좌 업로드 메소드: " + coursesListDTO.toString());
		
		List<CoursesDTO> courses = lecturesService.uploadCourses(coursesListDTO);
		
		JsonObject jsonData = new JsonObject();
		
		jsonData.addProperty("id", courses.get(0).getLectureId());
		
		model.addAttribute("data", jsonData);
		
		return "data_res"; // 강좌 업로드 완료되면 강의 디테일로 이동
	}
	
	
	//4. 강의 전체 목록 조회
	@ApiOperation(value = "강의 전체목록 조회 메소드", notes = "전체 강의를 조회")
	@GetMapping(value = "/lecture-list", produces = "application/json;charset=UTF-8")
	public String getAllLectures(Model model)throws MessageException, IOException {
		log.info("강의 전체목록 조회 메소드");
		try {
			model.addAttribute("data", lecturesService.getAllLectures());
		} catch (JsonIOException s) {
			System.out.println("JSONException");
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		} 
		return "data_res"; // WEB-INF/data_res.jsp
	}
	
	//5. 강의 정보페이지 + 강좌 목록 + 강좌 추가 버튼 + 강의바구니 버튼
	@ApiOperation(value = "강의 하나 정보조회 메소드", notes = "강의 id로 강의 정보를 조회")
	@GetMapping(value = "/detail/{title}", produces = "application/json;charset=UTF-8")
	public String getLectureDetail(Model model, @PathVariable int title) throws NotExistException {
		log.info("강의 하나 정보조회 메소드");
		//강의+강좌 정보 조회
		LectureCoursesDTO lecture = lecturesService.getLectureDetail(title);
		System.out.println(lecture.getTeachersApplyTeacherMembers().getMemId());
		model.addAttribute("lecture", lecture);
		
		return "lecture/lecture-detail"; //WEB-INF/lecture/lecture-detail.jsp
	}
	
	//6. 강좌 시청 watch-course?
	/* 1. MyLectures에서 lecture -> id로 조회
	 * 2. MyLectures에서 lecture -> courses -> courseId로 조회 V
	 * */
	@ApiOperation(value = "강의 하나 정보조회 메소드", notes = "강의 id로 강의 정보를 조회")
	@GetMapping(value = "/watch-course/{title}", produces = "application/json;charset=UTF-8")
	public String getOneCourse() {
		
		return "";
	}
	
	//7-1. 내 강의(학생: memId로 조회) - myLectures -> join fetch Members, Lectures 
	/* 간략하게 강의명(클릭시 디테일로 들어가야 함 -> 강의번호 필요함)과 강사명 목록만 나열하도록 함
	 * */
	
	//7-2. 내 강의(강사: ) - myLectures -> join fetch Lectures join fetch 
	/* 간략하게 강의명(클릭시 디테일로 들어가야 함 -> 강의번호 필요함)과 강사명 목록만 나열하도록 함
	 * */
	
	//8.강의 제목으로 부분검색
	//Model에 JSONArray데이터 담은 후 res.jsp로 forward전송
	/* 1. 강의 테이블 조회
	 * 2. 그에 따른 카테고리-강의 테이블 조회
	 * 3. 넘어가야 할 속성: 강의번호(조회용), 강사명, 강의제목, 이미지, 가격, 강의수, 한줄 설명, 수강학생 수, 카테고리명(배열)
	 * */
	@GetMapping(value = "/search-lecture/{title}", produces = "application/json;charset=UTF-8")
	public String searchLecture(Model model, @PathVariable String title) {
		log.info("searchLecture()호출: " + title);
		try {
			// 방 리스트를 데이터에 담아줌
			model.addAttribute("data", lecturesService.searchLectures(title));
		} catch (JsonIOException s) {
			
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		}
		return "data_res"; // WEB-INF/main_res.jsp
	}
	
	//9. 전체 카테고리 조회
	@GetMapping(value = "/category-all", produces = "application/json;charset=UTF-8")
	public String getAllCategory(Model model) {
		
		List<CategoryDTO> category = lecturesService.getAllCategory();
		
		model.addAttribute("category", category);
		
		return "lecture/lecture-list"; // WEB-INF/lecture/lecture-list.jsp
	}
	
	//10. 카테고리로 강의 검색
	@GetMapping(value = "/search-category/{title}", produces = "application/json;charset=UTF-8")
	public String searchCategory(Model model, @PathVariable int title) {
		log.info("searchCategory()호출: " + title);
		
		try {
			
			model.addAttribute("data", lecturesService.searchCategotyLecture(title));
			
		} catch (JsonIOException s) {
			
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		} 
		return "data_res"; // WEB-INF/main_res.jsp
	}
	
	//NotExistException 관련 예외처리
	@ExceptionHandler(value = NotExistException.class)
	public String notExistException(NotExistException ne, Model model) {
		System.out.println(ne.getMessage());
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "error"; //예: WEB-INF/error.jsp
	}
	
	// MessageException 관련 예외처리
	@ExceptionHandler(value = MessageException.class)
	public String messageExceptio(MessageException ne, Model model) {
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "error"; // 예: WEB-INF/error.jsp
	}
	
	
}
