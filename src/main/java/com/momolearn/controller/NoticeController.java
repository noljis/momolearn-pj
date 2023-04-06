package com.momolearn.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.dto.BoardDTO;
import com.momolearn.model.dto.BoardListDTO;
import com.momolearn.model.dto.BoardSaveDTO;
import com.momolearn.model.dto.LikesDTO;
import com.momolearn.model.service.BoardService;
import com.momolearn.model.service.LikesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes({"members"})
@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {
	
	private final BoardService boardService;
	
	private final LikesService likesService;
	
	@GetMapping
	public String getNoticeList(Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<BoardListDTO> listPage = boardService.pagingNotice(pageable);
		int nowPage = listPage.getPageable().getPageNumber()+1;
		int startPage = Math.max(1, listPage.getPageable().getPageNumber() -2);
		int endPage = Math.min(listPage.getPageable().getPageNumber() +2, listPage.getTotalPages());
		
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("listPage", listPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/notice";
	}
	
	@GetMapping("/writeform")
	public String writeForm(@ModelAttribute BoardSaveDTO dto) {
		
		return "board/noticeWriteForm";
		
	}
	
	@PostMapping
	public String write(@Valid BoardSaveDTO dto, BindingResult bindingResult) throws NotExistException{
		
		if(bindingResult.hasErrors()) {
			
			List<ObjectError> errorList = bindingResult.getAllErrors();
			
			for(ObjectError error : errorList) {
				
				log.info("등록 실패: "+error.getDefaultMessage());
				throw new NotExistException(error.getDefaultMessage());
			}
		}
		
		boardService.savePost(dto);
		
		return "redirect:/notice";
	}
	
	@GetMapping("/{comNo}")
	public String read(@PathVariable int comNo, Model model) throws NotExistException{

		BoardDTO dto = boardService.readPost(comNo);

		boardService.increaseViewCount(comNo);

		long likesCount = likesService.countLike(comNo);

		List<LikesDTO> likesList = likesService.getLikesList(comNo);
		
		model.addAttribute("dto", dto);
		model.addAttribute("likesCount", likesCount);
		model.addAttribute("likesList", likesList);
		model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd hh:mm"));
		
		return "board/noticeDetail";
	}
	
	@GetMapping("/updateForm/{comNo}")
	public String noticeUpdateForm(@PathVariable int comNo, Model model) throws NotExistException{

		model.addAttribute("dto", boardService.readPost(comNo));
		
		return "board/noticeUpdateForm";
	}
	
	@PutMapping("/{comNo}")
	public String updateNotice(@PathVariable int comNo, @Valid BoardSaveDTO dto, BindingResult bindingResult) throws NotExistException{
		
		if(bindingResult.hasErrors()) {

			List<ObjectError> errorList = bindingResult.getAllErrors();
			
			for(ObjectError error : errorList) {
				
				log.info("수정 실패: "+error.getDefaultMessage());
				throw new NotExistException(error.getDefaultMessage());
			}
		}
		
		boardService.updatePost(comNo, dto);
		return "redirect:/notice";
		
	}
	
	@DeleteMapping("/{comNo}")
	public String deleteNotice(@PathVariable int comNo) throws NotExistException {

		boardService.deletePost(comNo);
		
		return "redirect:/notice";
	}
	
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		
		return "error";
	}
	
}
