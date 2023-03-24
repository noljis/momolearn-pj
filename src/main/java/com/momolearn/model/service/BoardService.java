package com.momolearn.model.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.BoardRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.BoardDTO;
import com.momolearn.model.dto.BoardSaveDTO;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	private final MembersRepository membersRepository;
	
	public ModelMapper mapper = new ModelMapper();
	
	
	//게시글 작성
	@Transactional
	public Integer save(BoardSaveDTO dto) throws NotExistException{
		System.out.println("save() service-----");
		Members memEntity = membersRepository.findById(dto.getMembersMemId()).orElseThrow(()->new NotExistException("다시 로그인 해 주세요."));
		Board entity = boardRepository.save(dto.toEntity(memEntity));
		System.out.println(entity.toString());
		return entity.getComNo();
	}
	
	//게시글 보기
	public BoardDTO read(int comNo) throws NotExistException{
		System.out.println("read() service----------");
		Optional<Board> board = boardRepository.findById(comNo);
		Board entity = board.orElseThrow(()->new NotExistException("해당 게시글은 존재하지 않습니다."));
		return mapper.map(entity, BoardDTO.class);
	}
	
	//게시글 보기 - 조회수+1
	@Transactional
	public void increaseViewCount(int comNo) {
		System.out.println("increase() service------------");
		boardRepository.increaseCountByComNo(comNo);
	}
	
	//게시글 삭제
	@Transactional
	public void delete(int comNo) {
		System.out.println("delete() service --------------");
		boardRepository.deleteById(comNo);
	}

	//게시글 수정
	@Transactional
	public void update(int comNo, BoardSaveDTO dto) throws NotExistException{
		System.out.println("update() service---------------");
		Optional<Board> board = boardRepository.findById(comNo);
		Board entity = board.orElseThrow(()->new NotExistException("해당 게시글은 존재하지 않습니다."));
		entity.update(dto.getComTitle(), dto.getSubject(), dto.getComContent());
	}
	
	//목록보기+페이징
	public Page<BoardDTO> paging(Pageable pageable){
		Page<Board> entityPage = boardRepository.findAll(pageable);
		Page<BoardDTO> dtoPage = new BoardDTO().toDtoPage(entityPage);
		return dtoPage;
	}

}
