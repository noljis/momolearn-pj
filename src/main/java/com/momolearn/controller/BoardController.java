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
	
	//모든 게시글 목록
	@GetMapping
	public String getBoardList(Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		System.out.println("list()---------------");
		
		Page<BoardListDTO> listPage = boardService.paging(pageable);
		int nowPage = listPage.getPageable().getPageNumber()+1; //pagable은 0부터 시작
		int startPage = Math.max(1, listPage.getPageable().getPageNumber() -2);
		int endPage = Math.min(listPage.getPageable().getPageNumber() +3, listPage.getTotalPages());
		
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("listPage", listPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/list";
	}
	
	//게시글 작성창으로 이동
	@GetMapping("/writeForm")
	public String writeForm(@ModelAttribute BoardSaveDTO dto) {
		return "board/writeForm";
	}
	
	//게시글 작성
	@PostMapping
	public String writePost(@Valid BoardSaveDTO dto, BindingResult bindingResult) throws NotExistException{
		System.out.println("write()---------------");
		System.out.println(dto.toString());
		
		//유효성 검증
		if(bindingResult.hasErrors()) {
			//에러를 list에 저장
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for(ObjectError error : errorList) {
				log.info("등록 실패: "+error.getDefaultMessage());
				throw new NotExistException(error.getDefaultMessage());
			}
		}
		
		boardService.savePost(dto);
		return "redirect:/board";
	}
	
	
	
	//게시글 보기 + 조회수증가 + 좋아요개수 + 좋아요여부(?)
	@GetMapping("/{comNo}")
	public String readPost(@PathVariable int comNo, Model model) throws NotExistException{
		System.out.println("read()------------");
		//조회
		BoardDTO dto = boardService.readPost(comNo);
		//조회수증가
		boardService.increaseViewCount(comNo);
		//좋아요개수
		long likesCount = likesService.countLike(comNo);
		//좋아요여부 List
		List<LikesDTO> likesList = likesService.getLikesList(comNo);
		System.out.println(likesList);
		
		model.addAttribute("dto", dto);
		model.addAttribute("likesCount", likesCount);
		model.addAttribute("likesList", likesList);
		model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd hh:mm"));
		return "board/read";
	}
	
	//게시글 수정화면으로 이동
	@GetMapping("/updateForm/{comNo}")
	public String updateForm(@PathVariable int comNo, Model model) throws NotExistException{
		System.out.println("updateForm()----------------");
		model.addAttribute("dto", boardService.readPost(comNo));
		return "board/updateForm";
	}
	
	//게시글 수정
	@PutMapping("/{comNo}")
	public String updatePost(@PathVariable int comNo, @Valid BoardSaveDTO dto, BindingResult bindingResult) throws NotExistException{
		System.out.println("update()------------------");
		
		//유효성 검증
		if(bindingResult.hasErrors()) {
			//에러를 list에 저장
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for(ObjectError error : errorList) {
				log.info("수정 실패: "+error.getDefaultMessage());
				throw new NotExistException(error.getDefaultMessage());
			}
		}
		
		boardService.updatePost(comNo, dto);
		return "redirect:/board";
		
	}
	
	//게시글 삭제
	@DeleteMapping("/{comNo}")
	public String deletePost(@PathVariable int comNo) throws NotExistException {
		System.out.println("delete() ---------");
		boardService.deletePost(comNo);
		return "redirect:/board";
	}
	//검색+페이징
	@GetMapping("/search")
	public String searchPost(@RequestParam String searchType, @RequestParam String searchText, Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		System.out.println("searchPost()--------------");
		
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
	
	//다른회원글쓴목록찾기
	@GetMapping("/searchOneMemberPosts")
	public String searchOneMemberPosts(@RequestParam String searchType, @RequestParam String searchText, Model model, @PageableDefault(sort = "comNo", direction = Sort.Direction.DESC) Pageable pageable) {
		System.out.println("searchPost()--------------");
		
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
	
	//다중이미지 업로드
	@PostMapping("/image/upload")
	public ModelAndView uploadImage(@RequestParam Map<String, Object> map, MultipartHttpServletRequest request) throws NotExistException, IllegalStateException, IOException{
		
		ModelAndView mv = new ModelAndView("jsonView"); //ckeditor를 위한 설정.. WebConfig에 등록해둠
		List<MultipartFile> fileList = request.getFiles("upload"); //에디터에서 받아온파일 리스트. ckeditor에서는 파일을 보낼때 upload:파일 형식으로 넘어옴.
		
		String uploadPath = null;
		String fileUrl = null;
		
		for(MultipartFile uploadFile : fileList) {
			if(fileList.get(0).getSize()>0) {
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.indexOf("."));
				String newFileName = UUID.randomUUID() + ext; //파일이름 중복방지
				
				String realPath = request.getServletContext().getRealPath("/img/upload");//현재경로
				System.out.println("realPath:"+realPath);
				uploadPath = realPath + "/" + newFileName; //저장경로
				System.out.println("savePath:"+uploadPath);
				
				File file = new File(uploadPath); //저장경로에 파일 객체 생성
				if(!file.exists()) {
					file.mkdirs();
				}
				
				fileUrl = request.getContextPath()+"/img/upload/"+newFileName;
				uploadFile.transferTo(file);//업로드
			}
		}
		
		mv.addObject("uploaded", true); //업로드 완료 메시지
		mv.addObject("url", fileUrl); //업로드파일 경로
		
		return mv;
	}
	
	
	@ExceptionHandler
	public String exHandler(NotExistException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}
	
}
