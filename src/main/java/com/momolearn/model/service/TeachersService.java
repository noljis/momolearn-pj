package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.TeachersRepository;

@Service
public class TeachersService {
	
	@Autowired
	private TeachersRepository teachersDAO;

}
