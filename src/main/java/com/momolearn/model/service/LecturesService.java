package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.CoursesDAO;
import com.momolearn.model.LecturesDAO;
import com.momolearn.model.TeachersDAO;

@Service
public class LecturesService {
	
	@Autowired
	private TeachersDAO teachersDAO;
	
	@Autowired
	private LecturesDAO lecturesDAO;
	
	@Autowired
	private CoursesDAO coursesDAO;

}
