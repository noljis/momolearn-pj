package com.momolearn.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.service.LikesService;

import lombok.RequiredArgsConstructor;

@SessionAttributes({"members"})
@RestController
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikesController {
	
	private final LikesService likesService;
	
	
	
	@PostMapping("/{comNo}")
	public long likePost(@PathVariable int comNo, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		
		likesService.likePost(members.getMemId(), comNo);
		long likesCount = likesService.countLike(comNo);
		
		return likesCount;
	}
	
	
	@DeleteMapping("/{comNo}")
	public long cancelLike(@PathVariable int comNo, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		
		likesService.cancelLike(members.getMemId(), comNo);
		long likesCount = likesService.countLike(comNo);
		
		return likesCount;
	}
	
		
	@ExceptionHandler(NotExistException.class)
	public String exHandler(NotExistException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		
		return "error";
	}
}
