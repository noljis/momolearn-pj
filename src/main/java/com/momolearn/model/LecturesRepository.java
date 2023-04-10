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

	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findByTitleContaining(@Param("title") String title);
	
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members", "categoryLecture.category"})
	List<Lectures> findAll();
	
	@Query("SELECT l.title FROM Lectures l JOIN l.categoryLecture cl JOIN cl.category c WHERE c.cateId = :categoryId")
	List<String> findByCategoryLectureCategoryCateId(@Param("categoryId") int categoryId);
	
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members"})
	Lectures findById(@Param("id") int id);
	
	@EntityGraph(attributePaths = {"teachers.applyTeacher.members"})
	Lectures findByCoursesCourseId(@Param("courseId") int coursesId);

	@EntityGraph(attributePaths = {"teachers.applyTeacher.members"})
	List<Lectures> findByteachersApplyTeacherMembersMemId(String memId);

	List<Lectures> findByTeachersTeacherNo(int teacherNo);

	
	
}
