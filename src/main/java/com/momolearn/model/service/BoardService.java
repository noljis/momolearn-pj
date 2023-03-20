package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardDAO;

}
