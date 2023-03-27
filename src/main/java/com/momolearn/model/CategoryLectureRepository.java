package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.CategoryLecture;

@Repository
public interface CategoryLectureRepository extends JpaRepository<CategoryLecture, Integer>{

	//강의id에 해당하는 카테고리들 조회
	List<CategoryLecture> findByLectureId(int id);

	//카테고리id에 해당하는 
	List<CategoryLecture> findByCategoryCateId(int title);
}
