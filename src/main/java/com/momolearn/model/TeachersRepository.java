package com.momolearn.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Integer>{

	// 회원id와 승인여부(ApproveIsTrue)로 강사내역 조회
	@Query("select t from Teachers t join fetch t.applyTeacher at join fetch at.members m where m.memId = :id and at.approve = 'true'")
	Optional<Teachers> findByMemIdAndApprove(@Param("id") String id);
	
	// 강사 신청서 정보를 강사 테이블에 저장
	Teachers save(Teachers teachers); 
		
	//강사 전체 목록 조회
	List<Teachers> findAll();
	
	Optional<Teachers> findByApplyTeacherMembersMemId(String memId);

	Optional<Teachers> findByApplyTeacherId(int id);

	//Teachers save(TeachersDTO newTeacher);
}
