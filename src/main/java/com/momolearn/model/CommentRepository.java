package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momolearn.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
