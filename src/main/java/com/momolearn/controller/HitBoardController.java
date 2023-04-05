package com.momolearn.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.HitBoardDTO;
import com.momolearn.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("hit-board")
public class HitBoardController {
	
	private final BoardService boardService;
	
	
	@GetMapping
	public List<HitBoardDTO> getHitPosts(@RequestParam String criteria) throws NotExistException{
		return boardService.getHitPosts(criteria);
	}
	
	
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}
}
