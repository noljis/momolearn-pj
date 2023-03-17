package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Board;

@Repository
public interface BoardDAO extends JpaRepository<Board, String>{

}
