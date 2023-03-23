package com.momolearn.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Integer>{

	//회원ID로 강사정보 조회
	Optional<Teachers> findByApplyTeacherMembersMemId(String memId);

}
