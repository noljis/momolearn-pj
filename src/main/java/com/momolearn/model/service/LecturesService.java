package com.momolearn.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.CategoryLectureRepository;
import com.momolearn.model.CategoryRepository;
import com.momolearn.model.CoursesRepository;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.MyLecturesRepository;
import com.momolearn.model.dto.CategoryDTO;
import com.momolearn.model.dto.CoursesDTO;
import com.momolearn.model.dto.CoursesListDTO;
import com.momolearn.model.dto.LectureCoursesDTO;
import com.momolearn.model.dto.LecturesDTO;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.dto.MyLecturesDTO;
import com.momolearn.model.dto.MyLecturesTeacherDTO;
import com.momolearn.model.entity.Category;
import com.momolearn.model.entity.CategoryLecture;
import com.momolearn.model.entity.Courses;
import com.momolearn.model.entity.Lectures;
import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.MyLectures;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LecturesService {

	private final LecturesRepository lecturesRepository;

	private final CoursesRepository coursesRepository;

	private final CategoryRepository categoryRepository;

	private final CategoryLectureRepository categoryLectureRepository;
	
	private final MyLecturesRepository myLecturesRepository;

	private ModelMapper mapper = new ModelMapper();

	// 강의 업로드 후 강좌 등록을 위한 강의 조회(반환)
	@Transactional
	public LecturesDTO uploadLecture(LecturesDTO lectureDTO) throws MessageException {
		
		Lectures lectures = mapper.map(lectureDTO, Lectures.class);
		
		// 저장
		try {
			
			Lectures lecture = lecturesRepository.save(lectures);
			return mapper.map(lecture, LecturesDTO.class);
			
		} catch (Exception e) {
			
			throw new MessageException("강의 등록에 실패했습니다.");
		}
	}

	// 강의명 부분검색
	public JsonArray searchLectures(String likeTitle) throws NullPointerException{
		
		// likeTitle이 포함된 title 데이터 검색
		List<Lectures> lectures = lecturesRepository.findByTitleContaining(likeTitle);
		
		if (lectures.isEmpty()) throw new NullPointerException();
		
		return getLectureJson(lectures);
	}

	// 카테고리 및 카테고리-강의 저장
	/*
	 * 1. 카테고리 ,으로 split 2. 카테고리 테이블에 존재하는지 여부 확인(존재하지 않으면 save) 3. 카테고리-강의테이블에 저장
	 */
	@Transactional
	public void getCategory(String category, LecturesDTO lectures) {
		// 엔티티로 변환
		Lectures lecture = mapper.map(lectures, Lectures.class);
		// 1. 카테고리 ,으로 split
		String[] categories = category.split(",");

		for (String cateName : categories) {
			// 2. 카테고리 테이블에 존재하는지 여부 확인(존재하지 않으면 save)
			Category cate = categoryRepository.findByCateName(cateName)
					.orElseGet(() -> categoryRepository.save(new Category(cateName)));
			// 3. 카테고리-강의 테이블 저장
			CategoryLecture categoryLecture = new CategoryLecture(lecture, cate);
			categoryLectureRepository.save(categoryLecture);
		}

	}

	// 강좌 등록 CoursesListDTO: 속성 타입들이 List
	// 강좌 추가된만큼 lecture cnt ++
	@Transactional
	public List<CoursesDTO> uploadCourses(CoursesListDTO coursesList) throws NotExistException{
		System.out.println("강좌등록 서비스 메소드");
		List<Courses> courses = new ArrayList<>();
		Lectures lecture = null;
		if(coursesList != null) {
			//반복문으로 배열에 담아주기
			for(int i = 0; i < (coursesList.getLectureId().size()); i++) {
				
				lecture = lecturesRepository.findById(coursesList.getLectureId().get(i)).orElseThrow(() -> new NotExistException("강의가 존재하지 않습니다."));
				//builder로 저장
				Courses course = Courses.builder()
						.lecture(lecture)
					    .title(coursesList.getTitle().get(i))
					    .time(coursesList.getTime().get(i))
					    .url(coursesList.getUrl().get(i))
					    .build();
				
				courses.add(course);
			}
		}
		//강의의 강좌수 업데이트
		int addCnt = courses.size();
		
		lecture.setCnt(lecture.getCnt() + addCnt);
		
		lecturesRepository.save(lecture);
		
		return Arrays.asList(mapper.map(coursesRepository.saveAll(courses), CoursesDTO[].class));
	}

	// 모든 카테고리 조회
	public List<CategoryDTO> getAllCategory() {

		List<Category> category = categoryRepository.findAll();
		
		return Arrays.asList(mapper.map(category, CategoryDTO[].class));
	}

	// 전체 강의 조회
	public JsonArray getAllLectures() {

		List<Lectures> lectures = lecturesRepository.findAll();
		
		return getLectureJson(lectures);
	}

	// 카테고리로 강의 조회 cateId: 카테고리 번호
	public JsonArray searchCategotyLecture(int cateId) {
		
		List<String> title = lecturesRepository.findByCategoryLectureCategoryCateId(cateId);
		
		if (title.isEmpty()) throw new NullPointerException();
		
		List<Lectures> lectures = new ArrayList<>();
		
		for(String t : title) {
			
			lectures.addAll(lecturesRepository.findByTitleContaining(t));
			
		}
		
		return getLectureJson(lectures);
	}

	//강의번호로 강의-강좌 하나 조회
	public LectureCoursesDTO getLectureDetail(int title) throws NotExistException {
		
		Lectures lecture = lecturesRepository.findById(title);
		
		if(lecture == null) {
			
			new NotExistException("해당 강의가 존재하지 않습니다.");
		}
		
		return mapper.map(lecture, LectureCoursesDTO.class);
	}
	
	//강좌번호로 강좌 하나 조회
	public CoursesDTO getOneCourse(int title) throws NotExistException{
		
		Courses courses = coursesRepository.findById(title).orElseThrow(() -> new NotExistException("해당 강좌 정보가 존재하지 않습니다."));
		
		return mapper.map(courses, CoursesDTO.class);
	}

	//강의 번호에 속한 강좌들 조회
	public List<CoursesDTO> getCourses(int title) {
		
		List<Courses> courses =  coursesRepository.findAllByLectureId(title);
		
		return Arrays.asList(mapper.map(courses, CoursesDTO[].class));
	}
	
	//강의목록에 보여질 Json데이터 메소드
	public JsonArray getLectureJson(List<Lectures> lectures) {
		
		JsonObject lectureJson = null;
		JsonArray lecturesJson = new JsonArray();
		
		for (int i = 0; i < lectures.size(); i++) {
			
			ArrayList<String> category = new ArrayList<>();
			//해당 강의 번호로 카테고리 조회. member.getName, category.getateName, 
			//강사번호로 강사 조회
			lectureJson = new JsonObject();
			lectureJson.addProperty("id", lectures.get(i).getId());
			lectureJson.addProperty("title", lectures.get(i).getTitle());
			lectureJson.addProperty("image", lectures.get(i).getImage());
			lectureJson.addProperty("price", lectures.get(i).getPrice());
			lectureJson.addProperty("cnt", lectures.get(i).getCnt());
			lectureJson.addProperty("info", lectures.get(i).getInfo());
			lectureJson.addProperty("applyCnt", lectures.get(i).getApplyCnt());
			lectureJson.addProperty("teacher", lectures.get(i).getTeachers().getApplyTeacher().getMembers().getName());
			
			//카테고리 배열 담아주기
			for(int j = 0; j < lectures.get(i).getCategoryLecture().size(); j++) {
				
				category.add(lectures.get(i).getCategoryLecture().get(j).getCategory().getCateName());
			}
			
			lectureJson.addProperty("category", new Gson().toJson(category));
			// 후에 JSONArray에 담아서 json 배열로 만들기
			lecturesJson.add(lectureJson);
		}
		
		return lecturesJson;
	}

	//수강중인 강좌 조회. 강좌 id로 조회
	/* MyLectures -> lecture -> courses
	 * 조회 후 memId와 비교해서 일치하는게 있으면 true 없으면 예외 발생
	 * 강사의 경우 -> 해당 강좌 id로 members 조회 후 일치하면 true
	 * */
	public void checkMyLecture(int title, String memId) throws NotExistException{
		boolean result = false;
		//1. 회원일 경우 검증
		List<MyLectures> myLecture = myLecturesRepository.findByLectureCoursesCourseId(title);
		//2. 강사본인일 경우 검증
		Lectures lecture = lecturesRepository.findByCoursesCourseId(title);
		
		if(lecture.getTeachers().getApplyTeacher().getMembers().getMemId().equals(memId)) {
			
			result = true;
		}
		
		for(int i = 0; i < myLecture.size(); i++) {
			//로그인 회원이 강의를 올린 강사면 조회 가능
			if(myLecture.get(i).getMember().getMemId().equals(memId)) {
				
				result = true;
				break;
			}
		}
		
		if(result == false) {

			throw new NotExistException("현재 수강중인 강의가 아닙니다. 수강신청 후 이용해주세요.");
		
		}
	}
	
	//수강중인 강의 조회. 강의id로 조회 : 
	/* MyLectures -> lecture(id)
	 * not empty조건만 만족하면 되니까 MyLecturesDTO 반환
	 * */
	public MyLecturesDTO checkMyLectureByLecId(int lectureId, String memId) {
		
		MyLectures myLecture = myLecturesRepository.findByLectureIdAndMemberMemId(lectureId, memId);
		
		if(myLecture == null) {
			
			return null;
		}
		
		return mapper.map(myLecture, MyLecturesDTO.class);
	}

	//member로 수강중인 강의 조회. 필요한 속성: 강의, 회원, 강사명(lecture.teachers)
	public List<MyLecturesTeacherDTO> getMyLectures(MembersDTO member) {
		
		Members members = mapper.map(member, Members.class);
		
		List<MyLectures> myLecture = myLecturesRepository.findByMember(members);
		
		return Arrays.asList(mapper.map(myLecture, MyLecturesTeacherDTO[].class));
	}

	//member로 내가 올린 강의 조회. teachersApplyTeacherMembers
	public List<LectureCoursesDTO> getTeacherLectures(MembersDTO member) {
		
		Members members = mapper.map(member, Members.class);
		
		List<Lectures> lectures = lecturesRepository.findByteachersApplyTeacherMembersMemId(members.getMemId());
				
		return Arrays.asList(mapper.map(lectures, LectureCoursesDTO[].class));
	}

	//강의 업데이트
	@Transactional
	public CoursesDTO updateCourse(CoursesDTO course) throws NotExistException {
		
		Courses courses = coursesRepository.findById(course.getCourseId()).orElseThrow(() -> new NotExistException("해당 강좌 정보가 존재하지 않습니다."));
		
		courses.setTitle(course.getTitle());
		courses.setTime(course.getTime());
		courses.setUrl(course.getUrl());
		
		courses = coursesRepository.save(courses);
		
		return mapper.map(courses, CoursesDTO.class);
	}
	
}
