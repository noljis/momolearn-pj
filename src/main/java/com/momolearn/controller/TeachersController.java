package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momolearn.model.service.TeachersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("teachers")
public class TeachersController {
	
	@Autowired
	private TeachersService teachersService;

}
