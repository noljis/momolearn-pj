package com.momolearn.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.momolearn.model.dto.MyLecturesDTO;
import com.momolearn.model.dto.MyLecturesTeacherDTO;
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

	@GetMapping(value = "/upload-check", produces = "application/json;charset=UTF-8")
	public String checkUploadLecture(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		log.info(members + "와 승인여부(true)로 유효성 검사 컨트롤러");
		
		TeacherMemberDTO teacher = teachersService.getOneTeachers(members.getMemId());
		
		if(teacher != null) {
		
			model.addAttribute("teacher", teacher);
		
		}

		return "lecture/lecture-form";
	}

	@PostMapping(value = "/upload-lecture", produces = "application/json;charset=UTF-8")
	public String uploadLecture(Model model, @ModelAttribute LecturesDTO lectureDTO,
			@RequestParam("file") MultipartFile file, @RequestParam("category") String category) throws MessageException , IOException{
		
		log.info("강의 업로드 메소드: " + lectureDTO.getTitle() + category);
		
		if(file == null) {
			
			lectureDTO.setImage("default.jpg");
			
		}else {

			String thumbnail = fileService.getLectureImage(lectureDTO.getTitle(), file);
			lectureDTO.setImage(thumbnail);
				
		}

		LecturesDTO lecture = lecturesService.uploadLecture(lectureDTO);
		lecturesService.getCategory(category, lecture);
		model.addAttribute("lecture", lecture);

		return "redirect:courses-form/" + URLEncoder.encode(lecture.getTitle(), StandardCharsets.UTF_8) + "/" + lecture.getId();
		
	}
	
	@GetMapping(value = "/courses-form/{title}/{id}", produces = "application/json;charset=UTF-8")
	public String courseForm(Model model, @PathVariable String title, @PathVariable int id) {
		
		log.info("강좌 폼으로 이동: " + title + id);
		
		model.addAttribute("lectitle", title);
		model.addAttribute("id", id);
		
		return "lecture/course-form";
	}
	
	@ApiOperation(value = "강좌 업로드", notes = "강좌 업로드 데이터를 전송")
	@PostMapping(value = "/upload-course", produces = "application/json;charset=UTF-8")
	public String uploadCourse(Model model, @RequestBody CoursesListDTO coursesListDTO)throws NotExistException {
		
		log.info("강좌 업로드 메소드: " + coursesListDTO.toString());
		
		List<CoursesDTO> courses = lecturesService.uploadCourses(coursesListDTO);
		JsonObject jsonData = new JsonObject();
		
		jsonData.addProperty("id", courses.get(0).getLectureId());
		model.addAttribute("data", jsonData);
		
		return "data_res";
	}
	
	@ApiOperation(value = "강의 전체목록", notes = "강의 전체목록을 조회")
	@GetMapping(value = "/lecture-list", produces = "application/json;charset=UTF-8")
	public String getAllLectures(Model model)throws MessageException, IOException {
		
		log.info("강의 전체목록 조회 메소드");
		
		try {
			
			model.addAttribute("data", lecturesService.getAllLectures());
			
		} catch (JsonIOException s) {
			
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
			
		} 
		
		return "data_res";
		
	}
	
	@GetMapping(value = "/detail/{title}", produces = "application/json;charset=UTF-8")
	public String getLectureDetail(Model model, @PathVariable("title") int title, @ModelAttribute("members") MembersDTO member) throws NotExistException {
		log.info("강의 하나 정보조회 메소드: " + title);
		
		LectureCoursesDTO lecture = lecturesService.getLectureDetail(title);
		
		MyLecturesDTO myLecture = lecturesService.checkMyLectureByLecId(title, member.getMemId());
		
		model.addAttribute("lecture", lecture);
		model.addAttribute("myLecture", myLecture);
		
		return "lecture/lecture-detail"; //WEB-INF/lecture/lecture-detail.jsp
	}
	
	@GetMapping(value="/check-mylecture/{title}", produces = "application/json;charset=UTF-8")
	public String checkMyLecture(@PathVariable int title, @ModelAttribute("members") MembersDTO member) throws NotExistException {
		log.info("강좌 수강 여부 검증 : " + title + member.getMemId());
		
		lecturesService.checkMyLecture(title, member.getMemId());
		
		return "redirect:/lectures/watch-course/" + title;
	}
	
	@GetMapping(value = "/watch-course/{title}", produces = "application/json;charset=UTF-8")
	public String getOneCourse(Model model, @PathVariable int title) throws NotExistException {
		
		log.info("강좌 시청 메소드 강좌id: " + title);
		
		CoursesDTO course = lecturesService.getOneCourse(title);
		
		LectureCoursesDTO lecture = lecturesService.getLectureDetail(course.getLectureId());
		
		model.addAttribute("lecture", lecture);
		model.addAttribute("course", course);
		
		return "lecture/courses-view";
	}
	
	@GetMapping(value = "/my-lecture", produces = "application/json;charset=UTF-8")
	public String myLecture(Model model, @ModelAttribute("members") MembersDTO member) {
		log.info("myLecture 메소드");
		
		List<MyLecturesTeacherDTO> lecture = lecturesService.getMyLectures(member);

		if(member.getGrade().equals("teacher")) {
			
			List<LectureCoursesDTO> teacherLecture = lecturesService.getTeacherLectures(member);
			model.addAttribute("teacherLec", teacherLecture);
		}
		
		model.addAttribute("lecture", lecture);
		
		return "lecture/my-lecture";
	}
	
	
	@ApiOperation(value = "강의 부분검색", notes = "강의 제목으로 부분검색")
	@GetMapping(value = "/search-lecture/{title}", produces = "application/json;charset=UTF-8")
	public String searchLecture(Model model, @PathVariable String title) {
		log.info("searchLecture()호출: " + title);
		try {
			
			model.addAttribute("data", lecturesService.searchLectures(title));
			
		} catch (JsonIOException s) {
			
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
			
		} catch (NullPointerException ne) {
			
			System.out.println("NullPointerException");
			model.addAttribute("data", "해당 검색어가 포함된 강의가 없습니다.");
			ne.printStackTrace();
		}
		return "data_res";
	}
	
	@GetMapping(value = "/category-all", produces = "application/json;charset=UTF-8")
	public String getAllCategory(Model model) {
		log.info("전체 카테고리 조회 메소드");
		
		List<CategoryDTO> category = lecturesService.getAllCategory();
		
		model.addAttribute("category", category);
		
		return "lecture/lecture-list";
	}
	
	@ApiOperation(value = "카테고리로 강의 조회", notes = "카테고리에 해당하는 강의 조회")
	@GetMapping(value = "/search-category/{title}", produces = "application/json;charset=UTF-8")
	public String searchCategory(Model model, @PathVariable int title) {
		log.info("카테고리로 강의 조회 메소드. 카테고리Id: " + title);
		
		try {
			
			model.addAttribute("data", lecturesService.searchCategotyLecture(title));
			
		} catch (JsonIOException s) {
			
			model.addAttribute("data", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
			
		} catch (NullPointerException ne) {
			
			model.addAttribute("data", "해당 카테고리에 존재하는 강의가 없습니다.");
			ne.printStackTrace();
			
		}
		
		return "data_res";
		
	}
	
	@GetMapping(value = "/courses-update-form/{courseId}", produces = "application/json;charset=UTF-8")
	public String moveUpdateCourseForm(Model model, @PathVariable("courseId") int courseId) throws NotExistException {
		
		CoursesDTO course = lecturesService.getOneCourse(courseId);
		
		model.addAttribute("course", course);
		
		return "lecture/course-update-form";
	}
	
	
	@GetMapping(value = "/lecture-update-form/{lectureId}", produces = "application/json;charset=UTF-8")
	public String moveUpdateLectureForm(Model model, @PathVariable("lectureId") int lectureId) throws NotExistException {
		
		LectureCoursesDTO lecture = lecturesService.getLectureDetail(lectureId);
		
		model.addAttribute("lecture", lecture);
		
		return "lecture/lecture-update-form";
	}
	
	@PutMapping(value = "/update-lecture", produces = "application/json;charset=UTF-8")
	public String updateLecture(Model model, @ModelAttribute LecturesDTO lectureDTO,
			@RequestParam("file") MultipartFile file) throws NotExistException, Exception {
		log.info("강의 수정 메소드. 강의 Id: " + lectureDTO.getId());
		
		if(file == null) {
			
			lectureDTO.setImage("default.jpg");
			
		}else {

			String thumbnail = fileService.getLectureImage(lectureDTO.getTitle(), file);
			lectureDTO.setImage(thumbnail);
				
		}

		LecturesDTO lectureUpdate = lecturesService.updateLecture(lectureDTO);

		return "redirect:/lectures/update-success/" + lectureUpdate.getId();
		
	}
	
	@DeleteMapping(value="/delete-lecture/{lectureId}", produces = "application/json;charset=UTF-8")
	public String deleteLecture(@PathVariable int lectureId) throws NotExistException {
		
		lecturesService.deleteLecture(lectureId);
		
		return "redirect:/lectures/delete-success";
		
	}
	
	@GetMapping(value = "/delete-success", produces = "application/json;charset=UTF-8")
	public String deleteSuccess() {
		log.info("강의 삭제 성공뷰로 이동");
		
		return "lecture/delete-success";
	}
	
	
	@PutMapping(value = "/update-course", produces = "application/json;charset=UTF-8")
	public String updateCourse(Model model, @ModelAttribute CoursesDTO course) throws NotExistException, Exception {
		log.info("강좌 수정 메소드. 강좌 Id: " + course.getCourseId());
		
		CoursesDTO courseUpdate =  lecturesService.updateCourse(course);
		
		return "redirect:/lectures/update-success/" + courseUpdate.getLectureId();
	}
	
	
	@GetMapping(value = "/update-success/{lectureId}", produces = "application/json;charset=UTF-8")
	public String updateSuccess(Model model, @PathVariable int lectureId) throws NotExistException, Exception {
		log.info("강좌 수정 성공뷰로 이동. 강의 Id: " + lectureId);
		
		model.addAttribute("lectureId", lectureId);
		
		return "lecture/update-success";
	}
	
	@ExceptionHandler(value = NotExistException.class)
	public String notExistException(NotExistException ne, Model model) {

		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		
		return "error";
	}
	
	@ExceptionHandler(value = MessageException.class)
	public String messageException(MessageException ne, Model model) {
		
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		return "error";
	}
	
	@ExceptionHandler(HttpSessionRequiredException.class)
    public String handleSessionRequiredException(HttpSessionRequiredException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", "로그인 후 이용해주시기 바랍니다.");
		
        return "cart/error";
    }
	
	
	@ExceptionHandler(FileSizeLimitExceededException.class)
    public String fileSizeLimitExceededException(FileSizeLimitExceededException fe, Model model) {
		
		fe.printStackTrace();
		model.addAttribute("errorMsg", "썸네일 크기는 최대 3MB 까지만 업로드 가능합니다.");
		
        return "error";
    }
	
}
