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
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.LikesService;

import lombok.RequiredArgsConstructor;

@SessionAttributes({"members"})
@RestController
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikesController {
	
	private final LikesService likesService;
	
	//좋아요
	//https://coco-log.tistory.com/133
	@PostMapping("/{comNo}")
	public int likePost(@PathVariable int comNo, @ModelAttribute("members") Members members) throws NotExistException {
		System.out.println("likePost()-------------------");
		return likesService.likePost(members.getMemId(), comNo);
	}
	
	//취소
	@DeleteMapping("/{comNo}")
	public void cancelLike(@PathVariable int comNo, @ModelAttribute("members") Members members) throws NotExistException {
		System.out.println("cancelLike()--------------------");
		likesService.cancelLike(members.getMemId(), comNo);
	}
	
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}
}
