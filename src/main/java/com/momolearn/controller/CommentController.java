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
import com.momolearn.model.service.AlarmService;
import com.momolearn.model.service.CommentService;

import lombok.RequiredArgsConstructor;

@SessionAttributes({"members"})
@RestController
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	private final AlarmService alarmService;
	
	@PostMapping("/board/{comNo}/comment")
	public void writeComment(@PathVariable int comNo, @ModelAttribute("members") MembersDTO members, @RequestBody CommentSaveDTO dto) throws NotExistException {
		
		commentService.writeComment(members.getMemId(), comNo, dto);
		alarmService.writeCommentEvent(comNo);
	}
	
	@GetMapping("/board/{comNo}/comment")
	public List<CommentDTO> readComment(@PathVariable int comNo) throws NotExistException{
		List<CommentDTO> cmtList = commentService.readComment(comNo);
		return cmtList;
	}
	
	@PutMapping("/board/{comNo}/comment/{cmtNo}")
	public void updateComment(@PathVariable int comNo, @PathVariable int cmtNo, @RequestBody CommentDTO dto) throws NotExistException {
		commentService.updateComment(cmtNo, dto);
	}
	
	@DeleteMapping("/board/{comNo}/comment/{cmtNo}")
	public void deleteComment(@PathVariable int comNo, @PathVariable int cmtNo) throws NotExistException {
		commentService.deleteComment(cmtNo);
	}
	
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg",e.getMessage());
		return "error";
	}
}
