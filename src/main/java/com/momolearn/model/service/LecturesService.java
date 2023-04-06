package com.momolearn.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@Transactional
	public LecturesDTO uploadLecture(LecturesDTO lectureDTO) throws MessageException {
		
		Lectures lectures = mapper.map(lectureDTO, Lectures.class);
		
		try {
			
			Lectures lecture = lecturesRepository.save(lectures);
			
			return mapper.map(lecture, LecturesDTO.class);
			
		} catch (Exception e) {
			
			throw new MessageException("강의 등록에 실패했습니다.");
		}
	}

	public JsonArray searchLectures(String likeTitle) throws NullPointerException{
		
		List<Lectures> lectures = lecturesRepository.findByTitleContaining(likeTitle);
		
		if (lectures.isEmpty()) throw new NullPointerException();
		
		return getLectureJson(lectures);
	}

	@Transactional
	public void getCategory(String category, LecturesDTO lectures) {

		Lectures lecture = mapper.map(lectures, Lectures.class);
		String[] categories = category.split(",");

		for (String cateName : categories) {

			Category cate = categoryRepository.findByCateName(cateName)
					.orElseGet(() -> categoryRepository.save(new Category(cateName)));

			CategoryLecture categoryLecture = new CategoryLecture(lecture, cate);
			categoryLectureRepository.save(categoryLecture);
		}

	}

	@Transactional
	public List<CoursesDTO> uploadCourses(CoursesListDTO coursesList) throws NotExistException{

		List<Courses> courses = new ArrayList<>();
		Lectures lecture = null;
		
		if(coursesList != null) {

			for(int i = 0; i < (coursesList.getLectureId().size()); i++) {
				
				lecture = lecturesRepository.findById(coursesList.getLectureId().get(i)).orElseThrow(() -> new NotExistException("강의가 존재하지 않습니다."));

				Courses course = Courses.builder()
						.lecture(lecture)
					    .title(coursesList.getTitle().get(i))
					    .time(coursesList.getTime().get(i))
					    .url(coursesList.getUrl().get(i))
					    .build();
				
				courses.add(course);
			}
		}
		
		int addCnt = courses.size();
		
		lecture.setCnt(lecture.getCnt() + addCnt);
		
		lecturesRepository.save(lecture);
		
		return Arrays.asList(mapper.map(coursesRepository.saveAll(courses), CoursesDTO[].class));
		
	}

	public List<CategoryDTO> getAllCategory() {

		List<Category> category = categoryRepository.findAll();
		
		return Arrays.asList(mapper.map(category, CategoryDTO[].class));
	}

	public JsonArray getAllLectures() {

		List<Lectures> lectures = lecturesRepository.findAll();
		
		return getLectureJson(lectures);
	}

	public JsonArray searchCategotyLecture(int cateId) {
		
		List<String> title = lecturesRepository.findByCategoryLectureCategoryCateId(cateId);
		
		if (title.isEmpty()) throw new NullPointerException();
		
		List<Lectures> lectures = new ArrayList<>();
		
		for(String t : title) {
			
			lectures.addAll(lecturesRepository.findByTitleContaining(t));
			
		}
		
		return getLectureJson(lectures);
	}
	
	public LectureCoursesDTO getLectureDetail(int title) throws NotExistException {
		
		Lectures lecture = lecturesRepository.findById(title);
		
		if(lecture == null) {
			
			new NotExistException("해당 강의가 존재하지 않습니다.");
		}
		
		return mapper.map(lecture, LectureCoursesDTO.class);
	}
	
	public CoursesDTO getOneCourse(int title) throws NotExistException{
		
		Courses courses = coursesRepository.findById(title).orElseThrow(() -> new NotExistException("해당 강좌 정보가 존재하지 않습니다."));
		
		return mapper.map(courses, CoursesDTO.class);
	}

	public List<CoursesDTO> getCourses(int title) {
		
		List<Courses> courses =  coursesRepository.findAllByLectureId(title);
		
		return Arrays.asList(mapper.map(courses, CoursesDTO[].class));
	}
	
	public JsonArray getLectureJson(List<Lectures> lectures) {
		
		JsonObject lectureJson = null;
		JsonArray lecturesJson = new JsonArray();
		
		for (int i = 0; i < lectures.size(); i++) {
			
			ArrayList<String> category = new ArrayList<>();
			lectureJson = new JsonObject();
			
			lectureJson.addProperty("id", lectures.get(i).getId());
			lectureJson.addProperty("title", lectures.get(i).getTitle());
			lectureJson.addProperty("image", lectures.get(i).getImage());
			lectureJson.addProperty("price", lectures.get(i).getPrice());
			lectureJson.addProperty("cnt", lectures.get(i).getCnt());
			lectureJson.addProperty("info", lectures.get(i).getInfo());
			lectureJson.addProperty("applyCnt", lectures.get(i).getApplyCnt());
			lectureJson.addProperty("teacher", lectures.get(i).getTeachers().getApplyTeacher().getMembers().getName());
			
			for(int j = 0; j < lectures.get(i).getCategoryLecture().size(); j++) {
				
				category.add(lectures.get(i).getCategoryLecture().get(j).getCategory().getCateName());
			}
			
			lectureJson.addProperty("category", new Gson().toJson(category));
			lecturesJson.add(lectureJson);
		}
		
		return lecturesJson;
	}

	public void checkMyLecture(int title, String memId) throws NotExistException{
		boolean result = false;
		
		List<MyLectures> myLecture = myLecturesRepository.findByLectureCoursesCourseId(title);
		Lectures lecture = lecturesRepository.findByCoursesCourseId(title);
		
		if(lecture.getTeachers().getApplyTeacher().getMembers().getMemId().equals(memId)) {
			
			result = true;
		}
		
		for(int i = 0; i < myLecture.size(); i++) {
			
			if(myLecture.get(i).getMember().getMemId().equals(memId)) {
				
				result = true;
				break;
			}
		}
		
		if(result == false) {

			throw new NotExistException("현재 수강중인 강의가 아닙니다. 수강신청 후 이용해주세요.");
		
		}
	}
	
	public MyLecturesDTO checkMyLectureByLecId(int lectureId, String memId) {
		
		MyLectures myLecture = myLecturesRepository.findByLectureIdAndMemberMemId(lectureId, memId);
		
		if(myLecture == null) {
			
			return null;
		}
		
		return mapper.map(myLecture, MyLecturesDTO.class);
	}

	public List<MyLecturesTeacherDTO> getMyLectures(MembersDTO member) {
		
		Members members = mapper.map(member, Members.class);
		
		List<MyLectures> myLecture = myLecturesRepository.findByMember(members);
		
		return Arrays.asList(mapper.map(myLecture, MyLecturesTeacherDTO[].class));
	}

	public List<LectureCoursesDTO> getTeacherLectures(MembersDTO member) {
		
		Members members = mapper.map(member, Members.class);
		
		List<Lectures> lectures = lecturesRepository.findByteachersApplyTeacherMembersMemId(members.getMemId());
				
		return Arrays.asList(mapper.map(lectures, LectureCoursesDTO[].class));
	}
	
	@Transactional
	public LecturesDTO updateLecture(LecturesDTO lectureDTO) throws MessageException {
		
		Lectures lectures = lecturesRepository.findById(lectureDTO.getId());
		
		lectures.setTitle(lectureDTO.getTitle());
		lectures.setPrice(lectureDTO.getPrice());
		lectures.setImage(lectureDTO.getImage());
		lectures.setInfo(lectureDTO.getInfo());
		lectures.setDescription(lectureDTO.getDescription());
		
		try {
			
			lectures = lecturesRepository.save(lectures);
			return mapper.map(lectures, LecturesDTO.class);
			
		} catch (Exception e) {
			
			throw new MessageException("강의 수정에 실패했습니다.");
		}
	}

	@Transactional
	public CoursesDTO updateCourse(CoursesDTO course) throws NotExistException {
		
		Courses courses = coursesRepository.findById(course.getCourseId()).orElseThrow(() -> new NotExistException("해당 강좌 정보가 존재하지 않습니다."));
		
		courses.setTitle(course.getTitle());
		courses.setTime(course.getTime());
		courses.setUrl(course.getUrl());
		
		courses = coursesRepository.save(courses);
		
		return mapper.map(courses, CoursesDTO.class);
	}
	
	public void deleteLecture(int lectureId) throws NotExistException {
		
		if(lecturesRepository.findById(lectureId) != null) {
			
			lecturesRepository.deleteById(lectureId);
			
		}else {
			
			throw new NotExistException("삭제하려는 강의가 없습니다.");
			
		}
		
	}

}
