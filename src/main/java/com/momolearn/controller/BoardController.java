package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.momolearn.model.dto.BoardDTO;
import com.momolearn.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//모든 게시글 목록
	@GetMapping("/list")
	public String list(Model model) {
		System.out.println("list()---------------");
		model.addAttribute("list", boardService.findAll());
		return "forward:/page/board/board.html";
	}
	
	//게시글 작성창으로 이동
	@GetMapping("/writeForm")
	public String writeForm() {
		return "forward:/page/board/writeForm.jsp";
	}
	
	//게시글 작성
	@PostMapping(value = "/write", produces = "application/json;charset=utf-8")
	public String write(BoardDTO dto) {
		System.out.println("write()---------------");
		System.out.println(dto.toString());
		int comNo = boardService.save(dto);
		return "redirect:list";
	}
	
	//게시글 보기
	@GetMapping("/read")
	public String read(@RequestParam int comNo, Model model) {
		System.out.println("read()------------");
		BoardDTO dto = boardService.read(comNo);
		model.addAttribute("dto", dto);
		return "forward:/page/board/read.jsp";
	}
	
	//게시글 삭제
	@DeleteMapping("/delete")
	public String delete(@RequestParam int comNo) {
		System.out.println("delete() ---------");
		boardService.delete(comNo);
		return "redirect:list";
	}
}
