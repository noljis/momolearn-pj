package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	@EntityGraph(attributePaths = {"lecture", "member"})
	List<Cart> findByLectureId(int lecId);

	@EntityGraph(attributePaths = {"lecture", "member"})
	List<Cart> findByMemberMemId(String memId);

}
