//package com.momolearn.controller;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.momolearn.model.dto.BoardDTO;
//import com.momolearn.model.service.BoardService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("hit-board")
//public class HitBoardController {
//	
//	private final BoardService boardService;
//	
//	@GetMapping
//	public List<BoardDTO> getHitPosts(@RequestParam String criteria){
//		System.out.println("getHitPosts()----------------");
//		
//		return boardService.getHitPosts(criteria);
//		
//	}
//}
