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

	//강의 외 조회할 속성 member.getName(강사 -> 강사내역 -> 회원), category.getCateName(카테고리강의 -> 카테고리)
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findByTitleContaining(@Param("title") String title);
	
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findAll();
	
	//@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture", "categoryLecture.category"})
	@Query("SELECT l.title FROM Lectures l JOIN l.categoryLecture cl JOIN cl.category c WHERE c.cateId = :categoryId")
	List<String> findByCategoryLectureCategoryCateId(@Param("categoryId") int categoryId);
	
	//강의+강의에 종속된 강좌 조회
//	@EntityGraph(attributePaths = {"courses", "teachers.applyTeacher.members"})
	@Query("SELECT l from Lectures l join fetch l.teachers t join fetch t.applyTeacher at join fetch at.members m WHERE l.id=:id")
	Lectures findById(@Param("id") int id);

}
