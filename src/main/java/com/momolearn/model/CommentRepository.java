package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.momolearn.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query(value="select c.com_no from Comment c group by c.com_no order by count(c.com_no) desc limit 5", nativeQuery = true)
	List<Integer> findTop5ByOrderByCommentsDesc();
}
