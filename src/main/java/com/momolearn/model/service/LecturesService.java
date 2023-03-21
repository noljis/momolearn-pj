package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.CoursesRepository;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.TeachersRepository;

@Service
public class LecturesService {
	
	@Autowired
	private TeachersRepository teachersRepository;
	
	@Autowired
	private LecturesRepository lecturesRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;



}
