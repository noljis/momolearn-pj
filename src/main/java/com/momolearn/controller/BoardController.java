package com.momolearn.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("board")
public class BoardController {
	
	private final BoardService boardService;
	
	private final LikesService likesService;
	
	@GetMapping
	public String getBoardList(Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<BoardListDTO> listPage = boardService.paging(pageable);
		
		int nowPage = listPage.getPageable().getPageNumber()+1;
		int startPage = Math.max(1, listPage.getPageable().getPageNumber() -2);
		int endPage = Math.min(listPage.getPageable().getPageNumber() +3, listPage.getTotalPages());
		
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("listPage", listPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/list";
	}
	
	@GetMapping("/writeform")
	public String writeForm(@ModelAttribute BoardSaveDTO dto) {
		
		return "board/writeForm";
		
	}
	
	@PostMapping
	public String writePost(@Valid BoardSaveDTO dto, BindingResult bindingResult) throws NotExistException{
		System.out.println(dto.toString());
		
		if(bindingResult.hasErrors()) {
			
			List<ObjectError> errorList = bindingResult.getAllErrors();
			
			for(ObjectError error : errorList) {
				
				log.info("등록 실패: "+error.getDefaultMessage());
				throw new NotExistException(error.getDefaultMessage());
				
			}
		}
		
		boardService.savePost(dto);
		return "redirect:/board";
	}
	
	@GetMapping("/{comNo}")
	public String readPost(@PathVariable int comNo, Model model) throws NotExistException{
		
		BoardDTO dto = boardService.readPost(comNo);
		boardService.increaseViewCount(comNo);
		long likesCount = likesService.countLike(comNo);
		List<LikesDTO> likesList = likesService.getLikesList(comNo);
		
		model.addAttribute("dto", dto);
		model.addAttribute("likesCount", likesCount);
		model.addAttribute("likesList", likesList);
		model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd hh:mm"));
		
		return "board/read";
	}
	
	
	@GetMapping("/updateform/{comNo}")
	public String updateForm(@PathVariable int comNo, Model model) throws NotExistException{
		
		model.addAttribute("dto", boardService.readPost(comNo));
		
		return "board/updateForm";
	}
	
	@PutMapping("/{comNo}")
	public String updatePost(@PathVariable int comNo, @Valid BoardSaveDTO dto, BindingResult bindingResult) throws NotExistException{
		
		if(bindingResult.hasErrors()) {
			
			List<ObjectError> errorList = bindingResult.getAllErrors();
			
			for(ObjectError error : errorList) {
				
				log.info("수정 실패: "+error.getDefaultMessage());
				throw new NotExistException(error.getDefaultMessage());
				
			}
		}
		
		boardService.updatePost(comNo, dto);
		return "redirect:/board";
		
	}
	
	@DeleteMapping("/{comNo}")
	public String deletePost(@PathVariable int comNo) throws NotExistException {
		
		boardService.deletePost(comNo);
		
		return "redirect:/board";
	}
	
	
	@GetMapping("/search")
	public String searchPost(@RequestParam String searchType, @RequestParam String searchText, Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<BoardListDTO> listPage = boardService.searchPost(searchType, searchText, pageable);
		int nowPage = listPage.getPageable().getPageNumber()+1;
		int startPage = Math.max(1, listPage.getPageable().getPageNumber() -2);
		int endPage = Math.min(listPage.getPageable().getPageNumber() +2, listPage.getTotalPages());
		
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("listPage", listPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/list";
	}
	
	
	@GetMapping("/search-one-member-posts")
	public String searchOneMemberPosts(@RequestParam String searchType, @RequestParam String searchText, Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<BoardListDTO> listPage = boardService.searchPost(searchType, searchText, pageable);
		int nowPage = listPage.getPageable().getPageNumber()+1;
		int startPage = Math.max(1, listPage.getPageable().getPageNumber() -2);
		int endPage = Math.min(listPage.getPageable().getPageNumber() +2, listPage.getTotalPages());
		
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("listPage", listPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/memberpostslist";
	}
	
	
	@GetMapping("/search-likes")
	public String searchLikesPosts(@RequestParam String memId, Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<BoardListDTO> listPage = boardService.searchLikesPosts(memId, pageable);
		int nowPage = listPage.getPageable().getPageNumber()+1;
		int startPage = Math.max(1, listPage.getPageable().getPageNumber() -2);
		int endPage = Math.min(listPage.getPageable().getPageNumber() +2, listPage.getTotalPages());
		
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("listPage", listPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/likepostslist";
	}
	
	
	@PostMapping("/image/upload")
	public ModelAndView uploadImage(@RequestParam Map<String, Object> map, MultipartHttpServletRequest request) throws NotExistException, IllegalStateException, IOException{
		
		ModelAndView mv = new ModelAndView("jsonView");
		List<MultipartFile> fileList = request.getFiles("upload");
		
		String uploadPath = null;
		String fileUrl = null;
		
		for(MultipartFile uploadFile : fileList) {
			
			if(fileList.get(0).getSize()>0) {
				
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.indexOf("."));
				String newFileName = UUID.randomUUID() + ext;
				
				String realPath = request.getServletContext().getRealPath("/img/upload");
				uploadPath = realPath + "/" + newFileName;
				
				File file = new File(uploadPath);
				
				if(!file.exists()) {
					
					file.mkdirs();
					
				}
				
				fileUrl = request.getContextPath()+"/img/upload/"+newFileName;
				uploadFile.transferTo(file);
			}
		}
		
		mv.addObject("uploaded", true);
		mv.addObject("url", fileUrl);
		
		return mv;
	}
	
	@ExceptionHandler(NotExistException.class)
	public String exHandler(NotExistException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		
		return "error";
	}
	
}
