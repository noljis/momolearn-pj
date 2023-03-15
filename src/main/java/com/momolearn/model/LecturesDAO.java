package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Lectures;

@Repository
public interface LecturesDAO extends JpaRepository<Lectures, String>{

}
