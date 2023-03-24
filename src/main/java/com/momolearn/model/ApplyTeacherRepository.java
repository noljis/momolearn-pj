package com.momolearn.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;

@Repository

public interface ApplyTeacherRepository extends JpaRepository<ApplyTeacher, Integer> {

	//회원
	// 회원 id로 강사 신청서에 들어갈 기본 정보(아이디, 이름, 이메일) 조회
	@Query("select m.memId, m.name, m.email from Members m where m.memId =:id")
	public Optional<Members> findMemInfoByMemId(@Param("id") String id);
	
	//관리자
	//강사 신청 승인하기 : approve를 false -> true로 변경
	//이거 필요없다고... 자동 업데이트 된다고.. setapprove("true") 이렇게 하는거랬나 => 찾아보기
    @Modifying
    @Query("UPDATE ApplyTeacher t SET t.approve = 'true' WHERE t.members.memId = :id")
    void approve(@Param("id") String id);
    
	//강의
	// 회원id로 강사 1명 내역 조회
	public Optional<ApplyTeacher> findByMembersMemId(String id);

	// 회원id와 승인여부(ApproveIsTrue)로 강사내역 조회
	@Query("select t from ApplyTeacher t where t.members.memId = :id and t.approve = 'true'")
	public Optional<ApplyTeacher> findByMembersMemIdAndApprove(@Param("id") String id);

}
