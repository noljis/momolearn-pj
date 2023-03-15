package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.ApplyTeacher;

@Repository
public interface ApplyTeacherDAO extends JpaRepository<ApplyTeacher, String>{

}
