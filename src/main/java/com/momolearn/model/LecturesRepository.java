package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Lectures;

@Repository
public interface LecturesRepository extends JpaRepository<Lectures, Integer>{

	//강의명으로 부분 조회
	List<Lectures> findByTitleContaining(String title);
}
