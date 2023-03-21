package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
