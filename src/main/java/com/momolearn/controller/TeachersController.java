package com.momolearn.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
=======
import org.springframework.stereotype.Controller;
>>>>>>> feature/shwork
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("teachers")
@SessionAttributes({"members"})
@RequiredArgsConstructor
public class TeachersController {
	
	private final TeachersService teachersService;
	
	private final MembersService membersService;
	

	

}
