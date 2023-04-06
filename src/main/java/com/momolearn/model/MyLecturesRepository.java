package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.MyLectures;

@Repository
public interface MyLecturesRepository extends JpaRepository<MyLectures, Integer>{

	@EntityGraph(attributePaths = {"member", "lecture.courses"})
	List<MyLectures> findByLectureCoursesCourseId(int courseId);

	@EntityGraph(attributePaths = {"lecture", "member"})
	MyLectures findByLectureIdAndMemberMemId(int lectureId, String memId);
	
	@EntityGraph(attributePaths = {"lecture.teachers.applyTeacher.members", "member"})
	List<MyLectures> findByMember(@Param("members") Members members);

}
