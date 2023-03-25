package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Lectures;

@Repository
public interface LecturesRepository extends JpaRepository<Lectures, Integer>{

	//강의 외 조회할 속성 member.getName(강사 -> 강사내역 -> 회원), category.getCateName(카테고리강의 -> 카테고리)
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findByTitleContaining(@Param("title") String title);
	
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findAll();
	
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findByCategoryLectureCategoryCateId(int categoryId);
	
	//강의+강의에 종속된 강좌 조회
	@EntityGraph(attributePaths = {"courses"})
	Lectures findById(int id);

}
