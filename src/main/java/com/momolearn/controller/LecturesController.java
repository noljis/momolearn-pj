package com.momolearn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.CategoryDTO;
import com.momolearn.model.dto.LecturesDTO;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.FileService;
import com.momolearn.model.service.LecturesService;
import com.momolearn.model.service.MembersService;
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
	
	private final MembersService membersService; 
	
	private final FileService fileService;

	// 1. 강의 업로드 클릭시 유효성검사 후 강의 등록 폼으로 이동
	/*
	 * 검증 1. 현재 로그인중인지(세션id 존재하는지) 2. 현재 로그인한 유저의 등급이 강사인지 -> TeachersService에서 조회
	 * 필요한 것 1. 세션id
	 */
	@ApiOperation(value = "강의 업로드 클릭시 유효성검사 메소드", notes = "유효성검사 후 강의 등록 폼으로 이동")
	@RequestMapping(value = "/uploadcheck", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String uploadLectureCheck(Model model, @ModelAttribute("members") Members members) throws NotExistException {

		// 강사 찾기 teacher로 찾아야함
		TeachersDTO teacher = teachersService.getOneTeachers(members.getMemId());

		// 강사가 존재하면 강사의 회원정보 불러오기
		MembersDTO member = membersService.getOneMember(members.getMemId());

		System.out.println("결과: " + teacher);
		System.out.println("결과: " + member);

		model.addAttribute("teacher", teacher);
		model.addAttribute("member", member);

		return "lecture/upload-lecture"; // 강의 업로드 폼으로 이동
	}

	// 2. 강의 업로드
	/*
	 * 1. html에 작성된 속성 강의명(title), 강사번호(teacher_no), 사진(MultipartFile), 가격(price),
	 * 한줄설명(info), 상세설명(description) 2. id(autoincrement), cnt: default=0(강좌 등록할때마다
	 * 하나씩 update), regdate(@CreatedDate), applyCnt: default=0(학생이 수강신청 할때마다 하나씩
	 * update) 3. 이미지 저장 후 lectureDTO.setImage에 이름.확장자명으로 set 4. 카테고리, 카테고리-강좌 테이블에
	 * 카테고리 저장 5. 강의 등록 후 강좌 등록 폼으로 이동
	 */
	@ApiOperation(value = "강의 업로드 메소드", notes = "강의 등록 후 강좌 등록 화면으로 이동")
	@RequestMapping(value = "/uploadlecture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String uploadLecture(Model model, @ModelAttribute LecturesDTO lectureDTO,
			@RequestParam("file") MultipartFile file, @RequestParam("category") String category)
			throws MessageException, IOException {
		log.info("강의 업로드 컨트롤러: " + lectureDTO.getTitle() + category);
		// 썸네일 먼저 저장 -> 이름.확장자명으로 반환해서 setImage 대입

		lectureDTO.setImage(fileService.getLectureImage(lectureDTO.getTitle(), file));

		// 강의 등록
		LecturesDTO lecture = lecturesService.uploadlecture(lectureDTO);

//		//카테고리, 카테고리-강좌 저장
		lecturesService.getCategory(category, lecture);
//		
//		//강의 model에 저장
		model.addAttribute("lecture", lecture);

		return "forward: /page/lecture/upload-course.html"; // 강좌 업로드 폼으로 이동
	}
	
	
	//3. 강좌 업로드
	
	//4. 강의 목록 조회
	
	//5. 강의 정보페이지 + 강좌 목록
	
	//6. 강좌 시청
	
	//7. 수강신청
	
	//8.강의 제목으로 부분검색
	//Model에 JSONArray데이터 담은 후 res.jsp로 forward전송
	/* 1. 강의 테이블 조회
	 * 2. 그에 따른 카테고리-강의 테이블 조회
	 * 3. 넘어가야 할 속성: 강의번호(조회용), 강사명, 강의제목, 이미지, 가격, 강의수, 한줄 설명, 수강학생 수, 카테고리명(배열)
	 * */
	@GetMapping(value = "/searchLecture/{title}", produces = "application/json;charset=UTF-8")
	public String searchLecture(Model model, @PathVariable String title) {
		log.info("searchLecture()호출: " + title);
		List<LecturesDTO> lectures = lecturesService.searchLectures(title);
		try {
			// 배열이 비어있으면 String으로 예외던지기
			System.out.println(lectures);
			if (lectures.isEmpty()) throw new NullPointerException();
			
			JsonObject lectureJson = null;
			JsonArray lecturesJson = new JsonArray();
			
			for (int i = 0; i < lectures.size(); i++) {
				//해당 강의 번호로 카테고리 조회
				ArrayList<String> category = lecturesService.getCategory(lectures.get(i).getId());
				//강사번호로 강사 조회
				String teacher = teachersService.getOneteacher(lectures.get(i).getTeachersTeacherNo());
				lectureJson = new JsonObject();
				lectureJson.addProperty("id", lectures.get(i).getTitle());
				lectureJson.addProperty("title", lectures.get(i).getTitle());
				lectureJson.addProperty("image", lectures.get(i).getImage());
				lectureJson.addProperty("price", lectures.get(i).getPrice());
				lectureJson.addProperty("cnt", lectures.get(i).getCnt());
				lectureJson.addProperty("info", lectures.get(i).getInfo());
				lectureJson.addProperty("applyCnt", lectures.get(i).getApplyCnt());
				lectureJson.addProperty("teacher", teacher);
				//카테고리 배열 담아주기
				lectureJson.addProperty("category", new Gson().toJson(category));
				// 후에 JSONArray에 담아서 json 배열로 만들기
				lecturesJson.add(lectureJson);
			}
			// 방 리스트를 데이터에 담아줌
			System.out.println("컨트롤러: " + lecturesJson);
			model.addAttribute("data", lecturesJson);
		} catch (JsonIOException s) {
			System.out.println("JSONException");
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		} catch (NullPointerException ne) {
			System.out.println("NullPointerException");
			model.addAttribute("data", "검색된 강의가 없습니다.");
			ne.printStackTrace();
		}

		return "data_res"; // WEB-INF/main_res.jsp
	}
	
	//9. 전체 카테고리 조회
	@GetMapping(value = "/categoryall", produces = "application/json;charset=UTF-8")
	public String getAllCategory(Model model) {
		List<CategoryDTO> category = lecturesService.getAllCategory();
		model.addAttribute("category", category);
		return "lecture/lecture-list"; // WEB-INF/lecture/lecture-list.jsp
	}
	//NotExistException 관련 예외처리
	@ExceptionHandler
	public String notExistException(NotExistException ne, Model model) {
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "에러화면 이동"; //예: WEB-INF/showError.jsp
	}
	
	// MessageException 관련 예외처리
	@ExceptionHandler
	public String messageExceptio(MessageException ne, Model model) {
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "에러화면 이동"; // 예: WEB-INF/showError.jsp
	}
	


}
