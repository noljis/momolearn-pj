package com.momolearn.model.service;



import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.model.BoardRepository;
import com.momolearn.model.dto.BoardDTO;
import com.momolearn.model.entity.Board;

@Service
public class BoardService {
	
	@Autowired

	private BoardRepository boardDAO;


	private BoardRepository boardRepository;
	
	@Bean
	public ModelMapper mapper() {
	    ModelMapper mapper = new ModelMapper();
	    mapper.getConfiguration()
	            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
	            .setFieldMatchingEnabled(true);
	    
	    return mapper; 
	}
	
	
	
	//모든 게시글 목록
	public List<BoardDTO> findAll() {
		List<Board> posts = boardRepository.findAll();
		//TypeToken: Generic Type정보를 런타임에도 얻을 수 있다. 원래는 컴파일 타임에만 검사됨
		return mapper().map(posts, new TypeToken<List<BoardDTO>>() {}.getType());
	}
	
	//게시글 작성
	@Transactional
	public Integer save(BoardDTO dto) {
		System.out.println("save() service-----");
		System.out.println(mapper().map(dto, Board.class).getComContent());
		Board entity = boardRepository.save(mapper().map(dto, Board.class));
		System.out.println(entity.toString());
		return entity.getComNo();
	}
	
	//게시글 보기
	public BoardDTO read(int comNo) {
		System.out.println("read() service----------");
		Optional<Board> entity = boardRepository.findById(comNo);
		return mapper().map(entity, BoardDTO.class);
	}
	
	//게시글 삭제
	public void delete(int comNo) {
		System.out.println("delete() service --------------");
		boardRepository.deleteById(comNo);
	}

}
