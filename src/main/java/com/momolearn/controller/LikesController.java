package com.momolearn.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//좋아요
	@PostMapping("/{comNo}")
	public long likePost(@PathVariable int comNo, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		System.out.println("likePost()-------------------");
		likesService.likePost(members.getMemId(), comNo);
		long likesCount = likesService.countLike(comNo);
		return likesCount;
	}
	
	//취소
	@DeleteMapping("/{comNo}")
	public long cancelLike(@PathVariable int comNo, @ModelAttribute("members") MembersDTO members) throws NotExistException {
		System.out.println("cancelLike()--------------------");
		likesService.cancelLike(members.getMemId(), comNo);
		long likesCount = likesService.countLike(comNo);
		return likesCount;
	}
	
	//체크-실험
	@GetMapping("/{comNo}")
	public boolean checkLike(@PathVariable int comNo, String memId) throws NotExistException {
		System.out.println("checkLike()-----------");
		boolean check = likesService.checkLike(comNo, memId);
		System.out.println("checkLike:"+check);
		return check;
	}
		
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}
}
