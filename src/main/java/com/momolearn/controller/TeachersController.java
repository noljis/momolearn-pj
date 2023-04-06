package com.momolearn.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.Lectures;
import com.momolearn.model.service.TeachersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("teachers")
@RequiredArgsConstructor
public class TeachersController {
	

	private final TeachersService teachersService;
	
	private final LecturesRepository lecturesRepository;

	@GetMapping(value = "/list") 
	public String getTeacherList(Model model) {
		List<TeachersDTO> teachers = teachersService.getTeacherList();
		
		model.addAttribute("teachers", teachers);
		 
	    return "teachers/t-list";
	}
	
	@GetMapping(value = "/t-list", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAllTeachers(Model model) {
	    List<TeachersDTO> teachers = teachersService.getTeacherList();

	    JsonArray result = new JsonArray();

	    for (TeachersDTO i : teachers) {
	        JsonObject obj = new JsonObject();
	        obj.addProperty("teacherNo", i.getTeacherNo());
	        obj.addProperty("hope", i.getHope());
	        result.add(obj);
	    }
	    
	    model.addAttribute("data", result);

	    return result.toString();
	}

	@GetMapping(value = "/list/{teacherNo}")
	@ResponseBody
	public String getTeacherDetail(@PathVariable int teacherNo) throws NotExistException {
		
		TeachersDTO teacher = teachersService.getOneTeacher(teacherNo);
		String name = teachersService.getOneteacher(teacherNo);
		String profile = teachersService.getOneteacherPro(teacherNo);
		
		JsonArray result = new JsonArray();
		JsonObject obj = new JsonObject();
		obj.addProperty("name", name);
		obj.addProperty("profile", profile);
		obj.addProperty("teacherNo", teacher.getTeacherNo());
		obj.addProperty("hope", teacher.getHope());
		obj.addProperty("pfLink", teacher.getPfLink());
		obj.addProperty("intro", teacher.getIntro());
		result.add(obj);
		
		return result.toString();
	}

	@GetMapping(value = "/t-list/{teacherNo}/lec/{teacherNo}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getTeacherLectures(@PathVariable int teacherNo) {
	    List<Lectures> lectures = lecturesRepository.findByTeachersTeacherNo(teacherNo);
	    JsonArray result = new JsonArray();
	    for (Lectures lecture : lectures) {
	        JsonObject jsonObject = new JsonObject();
	        jsonObject.addProperty("id", lecture.getId());
	        jsonObject.addProperty("title", lecture.getTitle());
	        result.add(jsonObject);
	    }
	    
	    return result.toString();
	}
	
}