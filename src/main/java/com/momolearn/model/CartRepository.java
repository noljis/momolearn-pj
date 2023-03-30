package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Cart;
import com.momolearn.model.entity.Lectures;
import com.momolearn.model.entity.Members;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	@EntityGraph(attributePaths = {"lecture", "member"})
	List<Cart> findByLectureId(int lecId);

	@EntityGraph(attributePaths = {"lecture", "member"})
	List<Cart> findByMemberMemId(String memId);

	@Modifying
	@Query("DELETE FROM Cart c WHERE c.member = :member AND c.lecture = :lecture")
	int deleteByMemberAndLecture(@Param("member") Members member, @Param("lecture") Lectures lecture);

}
