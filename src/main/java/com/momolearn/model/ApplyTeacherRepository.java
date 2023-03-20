package com.momolearn.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.ApplyTeacher;

@Repository
public interface ApplyTeacherRepository extends JpaRepository<ApplyTeacher, String>{

	//회원id로 강사내역 조회
	public Optional<ApplyTeacher> findByMembersMemId(String id);
	
}
