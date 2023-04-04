package com.momolearn.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.ApplyTeacher;

@Repository
public interface ApplyTeacherRepository extends JpaRepository<ApplyTeacher, Integer> {

	Optional<ApplyTeacher> findByMembersMemId(String id);

	@Query("select t from ApplyTeacher t where t.members.memId = :id and t.approve = 'true'")
	Optional<ApplyTeacher> findByMembersMemIdAndApprove(@Param("id") String id);

}
