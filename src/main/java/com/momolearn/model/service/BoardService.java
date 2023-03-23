package com.momolearn.model.service;



import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.BoardRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.BoardDTO;
import com.momolearn.model.dto.BoardSaveDTO;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Members;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MembersRepository membersRepository;
	
	public ModelMapper mapper = new ModelMapper();
	
	
	//모든 게시글 목록
	public List<BoardDTO> findAll() {
		System.out.println("findAll() service-------------");
		List<Board> posts = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "comNo"));
		//TypeToken: Generic Type정보를 런타임에도 얻을 수 있다. 원래는 컴파일 타임에만 검사됨
		return mapper.map(posts, new TypeToken<List<BoardDTO>>() {}.getType());
	}
	
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
	
	//페이징
	@Transactional(readOnly = true)
	public Page<Board> paging(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

}
