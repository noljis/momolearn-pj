package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.TeachersDAO;

@Service
public class TeachersService {
	
	@Autowired
	private TeachersDAO teachersDAO;

}
