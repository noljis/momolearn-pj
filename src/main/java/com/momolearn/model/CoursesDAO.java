package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Courses;

@Repository
public interface CoursesDAO extends JpaRepository<Courses, String>{

}
