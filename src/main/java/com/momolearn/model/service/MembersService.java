package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.MembersDAO;

@Service
public class MembersService {
	
	@Autowired
	private MembersDAO membersDAO;

}
