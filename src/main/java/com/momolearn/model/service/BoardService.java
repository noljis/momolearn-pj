package com.momolearn.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.BoardRepository;
import com.momolearn.model.CommentRepository;
import com.momolearn.model.LikesRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.BoardDTO;
import com.momolearn.model.dto.BoardListDTO;
import com.momolearn.model.dto.BoardSaveDTO;
import com.momolearn.model.dto.HitBoardDTO;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final MembersRepository membersRepository;
	private final LikesRepository likesRepository;
	private final CommentRepository commentRepository;
	
	public ModelMapper mapper = new ModelMapper();
	
	
	@Transactional
	public void savePost(BoardSaveDTO dto) throws NotExistException{
		System.out.println("save() service-----");
		Members memEntity = membersRepository.findById(dto.getMembersMemId()).orElseThrow(()->new NotExistException("다시 로그인 해 주세요."));
		Board entity = boardRepository.save(dto.toEntity(memEntity));
		System.out.println(entity.toString());
	}
	
	public BoardDTO readPost(int comNo) throws NotExistException{
		System.out.println("read() service----------");
		Board entity = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글은 존재하지 않습니다."));
		BoardDTO dto = new BoardDTO(entity);
		return dto;
	}
	
	@Transactional
	public void increaseViewCount(int comNo) {
		System.out.println("increase() service------------");
		boardRepository.increaseCountByComNo(comNo);
	}
	
	@Transactional
	public void deletePost(int comNo) throws NotExistException {
		System.out.println("delete() service --------------");
		Board board = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글은 존재하지 않습니다."));
		boardRepository.delete(board);
	}

	@Transactional
	public void updatePost(int comNo, BoardSaveDTO dto) throws NotExistException{
		System.out.println("update() service---------------");
		Optional<Board> board = boardRepository.findById(comNo);
		Board entity = board.orElseThrow(()->new NotExistException("해당 게시글은 존재하지 않습니다."));
		entity.update(dto.getComTitle(), dto.getSubject(), dto.getComContent());
	}
	
	public Page<BoardListDTO> paging(Pageable pageable){
		Page<Board> entityPage = boardRepository.findByType("community", pageable);
		Page<BoardListDTO> dtoPage = entityPage.map(e->new BoardListDTO(e));
		return dtoPage;
	}
	
	public Page<BoardListDTO> pagingNotice(Pageable pageable){
		Page<Board> entityPage = boardRepository.findByType("notice", pageable);
		Page<BoardListDTO> dtoPage = entityPage.map(e->new BoardListDTO(e));
		return dtoPage;
	}
	
	public Page<BoardListDTO> searchPost(String searchType, String searchText, Pageable pageable){
		System.out.println("searchPost() service-----------------");
		Page<Board> entityPage = null;
		
		if("title".equals(searchType)){
			entityPage = boardRepository.findByComTitleContaining(searchText, pageable);
		}else if("content".equals(searchType)) {
			entityPage = boardRepository.findByComContentContaining(searchText, pageable);
		}else {
			entityPage = boardRepository.findByMembers_MemIdContaining(searchText, pageable);
		}
		
		Page<BoardListDTO> dtoPage = entityPage.map(e->new BoardListDTO(e));
		return dtoPage;
	}
	
	public List<HitBoardDTO> getHitPosts(String criteria) throws NotExistException {
		System.out.println("getHitPosts() service-----------------------");
		
		List<HitBoardDTO> dtoList = null;
		
		if("comment".equals(criteria)) {
			List<Integer> selectedList = commentRepository.findTop5ByOrderByCommentsDesc();
			List<Board> entityList = boardRepository.findAllById(selectedList);
			dtoList = entityList.stream().map(e->new HitBoardDTO(e)).collect(Collectors.toList());
			
		}else if("likes".equals(criteria)) {
			List<Integer> selectedList = likesRepository.findTop5ByOrderByLikesDesc();
			selectedList.stream().forEach(e->System.out.print(e));
			List<Board> entityList = boardRepository.findAllById(selectedList);
			dtoList = entityList.stream().map(e->new HitBoardDTO(e)).collect(Collectors.toList());
			
		}else {
			List<Board> entityList = boardRepository.findTop5ByOrderByComViewCountDesc();
			dtoList = entityList.stream().map(e->new HitBoardDTO(e)).collect(Collectors.toList());
		}
		return dtoList;
	}

}
