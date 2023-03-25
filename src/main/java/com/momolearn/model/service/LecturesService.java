package com.momolearn.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.CategoryLectureRepository;
import com.momolearn.model.CategoryRepository;
import com.momolearn.model.CoursesRepository;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.CategoryDTO;
import com.momolearn.model.dto.CoursesDTO;
import com.momolearn.model.dto.LecturesDTO;
import com.momolearn.model.entity.Category;
import com.momolearn.model.entity.CategoryLecture;
import com.momolearn.model.entity.Courses;
import com.momolearn.model.entity.Lectures;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LecturesService {

	private TeachersRepository teachersRepository;

	private final LecturesRepository lecturesRepository;

	private final CoursesRepository coursesRepository;

	private final CategoryRepository categoryRepository;

	private final CategoryLectureRepository categoryLectureRepository;

	private ModelMapper mapper = new ModelMapper();

	// 강의 업로드 후 강좌 등록을 위한 강의 조회
	public LecturesDTO uploadLecture(LecturesDTO lectureDTO) throws MessageException {
		System.out.println("강의 등록 메소드 uploadLecture" + lectureDTO.getTitle());
		Lectures lectures = mapper.map(lectureDTO, Lectures.class);
		System.out.println(lectures.getTitle());
		// 저장
		try {
			Lectures lecture = lecturesRepository.save(lectures);
			return mapper.map(lecture, LecturesDTO.class);
		} catch (Exception e) {
			throw new MessageException("강의 등록에 실패했습니다.");
		}
	}

	// 강의 전체 목록 조회
	public List<LecturesDTO> getLectureLists() {

		List<Lectures> leclist = lecturesRepository.findAll();

		return Arrays.asList(mapper.map(leclist, LecturesDTO[].class));
	}

	// 강의명 부분검색
	public JsonArray searchLectures(String likeTitle) {
		// likeTitle이 포함된 title 데이터 검색
		List<Lectures> lectures = lecturesRepository.findByTitleContaining(likeTitle);
		System.out.println(lectures);
		return getLectureJson(lectures);
	}

	// 카테고리 및 카테고리-강의 저장
	/*
	 * 1. 카테고리 ,으로 split 2. 카테고리 테이블에 존재하는지 여부 확인(존재하지 않으면 save) 3. 카테고리-강의테이블에 저장
	 */
	public void getCategory(String category, LecturesDTO lectures) {
		// 엔티티로 변환
		Lectures lecture = mapper.map(lectures, Lectures.class);
		System.out.println(lecture.getId());
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

	// 강의 등록
	public CoursesDTO uploadCourse(CoursesDTO coursesDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	// 강의 번호로 카테고리-강의 검색
	public ArrayList<String> getCategory(int id) {
		
		List<CategoryLecture> categoryLectures = categoryLectureRepository.findByLectureId(id);
		ArrayList<String> category = new ArrayList<>();
		
		if (categoryLectures != null) {
			
			for (CategoryLecture categoryLecture : categoryLectures) {
				
				category.add(categoryLecture.getCategory().getCateName());
			}
		}
		
		return category;
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

	// 카테고리로 강의 조회
	public List<LecturesDTO> searchCategotyLecture(int title) {
		
		List<CategoryLecture> categorylecture = categoryLectureRepository.findByCategoryCateId(title);
		ArrayList<Lectures> lectures = new ArrayList<>();
		
		if (categorylecture != null) {
			
			for (CategoryLecture categoryLecture : categorylecture) {
				
			 Optional<Lectures> lecture = lecturesRepository.findById(categoryLecture.getLecture().getId());
		     lecture.ifPresent(lectures::add);			
		    }
			
		}
		
		return Arrays.asList(mapper.map(lectures, LecturesDTO[].class));
	}

	//강의번호로 강의 하나 조회
	public LecturesDTO getLectureDetail(int title) throws NotExistException {
		
		Lectures lecture = lecturesRepository.findById(title).orElseThrow(() -> new NotExistException("해당 강의가 존재하지 않습니다."));
		
		return mapper.map(lecture, LecturesDTO.class);
	}

	//강의 번호에 속한 강좌들 조회
	public List<CoursesDTO> getCourses(int title) {
		
		List<Courses> courses =  coursesRepository.findAllByLectureId(title);
		
		return Arrays.asList(mapper.map(courses, CoursesDTO[].class));
	}
	
	//강의목록에 보여질 Json데이터 메소드
	public JsonArray getLectureJson(List<Lectures> lectures) {
		System.out.println(lectures.get(0).getCategoryLecture().get(0).getCategory().getCateName());
		JsonObject lectureJson = null;
		JsonArray lecturesJson = new JsonArray();
		ArrayList<String> category = new ArrayList<>();
		for (int i = 0; i < lectures.size(); i++) {
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

}
