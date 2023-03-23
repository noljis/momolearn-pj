package com.momolearn.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.exception.MessageException;
import com.momolearn.model.CategoryLectureRepository;
import com.momolearn.model.CategoryRepository;
import com.momolearn.model.CoursesRepository;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.CategoryDTO;
import com.momolearn.model.dto.LecturesDTO;
import com.momolearn.model.entity.Category;
import com.momolearn.model.entity.CategoryLecture;
import com.momolearn.model.entity.Lectures;

@Service
public class LecturesService {
	
	@Autowired
	private TeachersRepository teachersRepository;
	
	@Autowired
	private LecturesRepository lecturesRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryLectureRepository categoryLectureRepository;

	
	private ModelMapper mapper = new ModelMapper();
	

	//강의 업로드 후 강좌 등록을 위한 강의 조회
	public LecturesDTO uploadlecture(LecturesDTO lectureDTO) throws MessageException {
		
		Lectures lectures = mapper.map(lectureDTO, Lectures.class);
		System.out.println(lectures.getTitle());
		//저장
		try {
			Lectures lecture = lecturesRepository.save(lectures);
			return mapper.map(lecture, LecturesDTO.class);
		}catch(Exception e) {
			throw new MessageException("강의 등록에 실패했습니다."); 
		}
	}
	
	//강의 전체 목록 조회
	public List<LecturesDTO> getLectureLists() {
		
		List<Lectures> leclist = lecturesRepository.findAll();
		

		return Arrays.asList(mapper.map(leclist, LecturesDTO[].class));
	}
	
	//강의명 부분검색
	public List<LecturesDTO> searchLectures(String likeTitle) {
		//likeTitle이 포함된 title 데이터 검색
		List<Lectures> lectures = lecturesRepository.findByTitleContaining(likeTitle);
		
		return Arrays.asList(mapper.map(lectures, LecturesDTO[].class));
	}
	
	//카테고리 및 카테고리-강의 저장
	/* 1. 카테고리 ,으로 split
	 * 2. 카테고리 테이블에 존재하는지 여부 확인(존재하지 않으면 save)
	 * 3. 카테고리-강의테이블에 저장
	 * */
	public void getCategory(String category, LecturesDTO lectures) {
		//엔티티로 변환
		Lectures lecture = mapper.map(lectures, Lectures.class);
		System.out.println(lecture.getId());
		//1. 카테고리 ,으로 split
		String[] categories = category.split(",");
		
		for(String cateName : categories) {
			//2. 카테고리 테이블에 존재하는지 여부 확인(존재하지 않으면 save)
			Category cate = categoryRepository.findByCateName(cateName)
							.orElseGet(() -> categoryRepository.save(new Category(cateName)));
			//3. 카테고리-강의 테이블 저장
			CategoryLecture categoryLecture = new CategoryLecture(lecture, cate);
			categoryLectureRepository.save(categoryLecture);
		}
		
	}
	
	//강의 번호로 카테고리-강의 검색
	public ArrayList<String> getCategory(int id) {
		List<CategoryLecture> categoryLectures = categoryLectureRepository.findBylectureId(id);
		ArrayList<String> category = new ArrayList<>();
		if (categoryLectures != null) {
			for (CategoryLecture categoryLecture : categoryLectures) {
				category.add(categoryLecture.getCategory().getCateName());
		    }
		}
		return category;
	}
	//모든 카테고리 조회
	public List<CategoryDTO> getAllCategory() {
		
		List<Category> category = categoryRepository.findAll();
		
		return Arrays.asList(mapper.map(category, CategoryDTO[].class));
	}


}
