package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Lectures;

@Repository
public interface LecturesRepository extends JpaRepository<Lectures, Integer>{

	//강의명으로 부분 조회
//	@Query("select l from Lectures l join fetch l.teachers t join fetch t.applyTeacher at join fetch at.members m join fetch l.categoryLecture cl join fetch cl.category c where l.title like %:title%")
//	List<Lectures> findByTitleContaining(@Param("title") String title);
	
	//강의 외 조회할 속성 member.getName(강사 -> 강사내역 -> 회원), category.getCateName(카테고리강의 -> 카테고리)
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findByTitleContaining(@Param("title") String title);
	
	//강의 외 조회할 속성 member.getName(강사 -> 강사내역 -> 회원), category.getCateName(카테고리강의 -> 카테고리)
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findAll();

}
