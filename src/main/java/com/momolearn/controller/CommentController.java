package com.momolearn.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.CommentDTO;
import com.momolearn.model.dto.CommentSaveDTO;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.service.CommentService;

import lombok.RequiredArgsConstructor;

@SessionAttributes({"members"})
@RestController
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@PostMapping("/board/{comNo}/comment")
	public int writeComment(@PathVariable int comNo, @ModelAttribute("members") MembersDTO members, @RequestBody CommentSaveDTO dto) throws NotExistException {
		System.out.println("writeComment()-------------");
		return commentService.writeComment(members.getMemId(), comNo, dto);
	}
	
	@GetMapping("/board/{comNo}/comment")
	public List<CommentDTO> readComment(@PathVariable int comNo) throws NotExistException{
		List<CommentDTO> cmtList = commentService.readComment(comNo);
		return cmtList;
	}
	
	@PutMapping("/board/{comNo}/comment/{cmtNo}")
	public int updateComment(@PathVariable int comNo, @PathVariable int cmtNo, CommentSaveDTO dto) throws NotExistException {
		System.out.println("updateComment()------------");
		System.out.println(comNo+"ㅇ"+cmtNo+"ㅇ"+dto.getCmtContent());
		commentService.updateComment(cmtNo, dto);
		return cmtNo;
	}
	
	@DeleteMapping("/board/{comNo}/comment/{cmtNo}")
	public int deleteComment(@PathVariable int comNo, @PathVariable int cmtNo) throws NotExistException {
		commentService.deleteComment(cmtNo);
		return cmtNo;
	}
	
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg",e.getMessage());
		return "error";
	}
}
