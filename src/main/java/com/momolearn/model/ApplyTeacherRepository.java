package com.momolearn.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.ApplyTeacher;

@Repository

public interface ApplyTeacherRepository extends JpaRepository<ApplyTeacher, Integer>{

	//회원id로 강사내역 조회
		public Optional<ApplyTeacher> findByMembersMemId(String id);
		
		//회원id와 승인여부(ApproveIsTrue)로 강사내역 조회
		@Query("select t from ApplyTeacher t where t.members.memId = :id and t.approve = 'true'")
		public Optional<ApplyTeacher> findByMembersMemIdAndApprove(@Param("id") String id);
}
