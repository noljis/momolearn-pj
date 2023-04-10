package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{

	List<Courses> findAllByLectureId(int lectureId);
}
