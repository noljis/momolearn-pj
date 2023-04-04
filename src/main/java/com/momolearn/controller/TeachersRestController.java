package com.momolearn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.dto.TeachersListDTO;
import com.momolearn.model.service.ApplyTeacherService;
import com.momolearn.model.service.MembersService;
import com.momolearn.model.service.TeachersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("teachers")
@RequiredArgsConstructor
public class TeachersRestController {

	private final TeachersService teachersService;
	private final ApplyTeacherService applyTeacherService;
	private final MembersService membersService;

	// O 강사 전체 리스트
	@GetMapping(value = "/t-list")
	public List<TeachersDTO> getAllTeachers() {
		List<TeachersDTO> teachers = teachersService.getTeacherList();
		return teachers;
	}

	@GetMapping("/t-list/{id}")
	public Map<String, Object> getTeacher(@PathVariable int id) throws NotExistException {
		TeachersDTO teacher = teachersService.getOneTeacher(id);
		String name = teachersService.getOneteacher(id);
		String profile = teachersService.getOneteacherPro(id);
		
		Map<String, Object> result = new HashMap<>();
		result.put("name", name);
		result.put("profile", profile);
		result.put("teacherNo", teacher.getTeacherNo());
		result.put("hope", teacher.getHope());
		result.put("pfLink", teacher.getPfLink());
		result.put("intro", teacher.getIntro());

		return result;

	}

	
	
//	@GetMapping(value = "/t-list")
//	public List<TeachersListDTO> getAllTeachers() {
//		List<TeachersListDTO> teachers = teachersService.getAllTeacherList();
//		return teachers;
//	}

//	public Map<String, Object> getAllTeachers() {
//		List<TeachersDTO> teachers = teachersService.getTeacherList();
//        String name = teachersService.getOneteacher();
//        String profile = teachersService.getOneteacherPro(id); 
//        
//		return teachers;
//	}
	
	
	
	/*
	 * put,delete : form 태그로 X form 태그 -> get, post 요청 : key=value 형태로 자바스크립트로 ajax
	 * or axios 요청 + 데이터는 json으로 통일
	 * 
	 * form:form 태그 : put,delete 까지 다 가능 -> but 사용 X
	 * 
	 * 스프링 컨트롤러의 파싱 전략 key=value 데이터로 자동 파싱 -> 오브젝트로 파싱 가능 but 세터가 없으면 작동 X
	 * 
	 * 
	 */
}
