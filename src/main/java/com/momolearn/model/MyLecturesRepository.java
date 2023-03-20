package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.MyLectures;

@Repository
public interface MyLecturesRepository extends JpaRepository<MyLectures, String>{

}
