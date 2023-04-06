package com.momolearn.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.BoardRepository;
import com.momolearn.model.CommentRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.CommentDTO;
import com.momolearn.model.dto.CommentSaveDTO;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Comment;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	
	private final MembersRepository membersRepository;
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public void writeComment(String memId, int comNo, CommentSaveDTO dto) throws NotExistException {
		
		Members member = membersRepository.findById(memId).orElseThrow(()->new NotExistException("다시 로그인 해 주세요."));
		Board board = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글이 존재하지 않습니다."));
		dto.setMembers(member);
		dto.setBoard(board);
		
		commentRepository.save(dto.toEntity()).getCmtNo();
		
	}

	public List<CommentDTO> readComment(int comNo) throws NotExistException{
		
		Board board = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글이 존재하지 않습니다."));
		List<Comment> entityList = board.getComments();
		List<CommentDTO> cmtList = entityList.stream().map(CommentDTO::new).collect(Collectors.toList());
		
		return cmtList;
	}

	@Transactional
	public void updateComment(int cmtNo, CommentDTO dto) throws NotExistException {
		
		Comment comment = commentRepository.findById(cmtNo).orElseThrow(()->new NotExistException("해당 댓글이 존재하지 않습니다."));
		comment.update(dto.getCmtContent());
		
	}
	
	@Transactional
	public void deleteComment(int cmtNo) throws NotExistException {
		
		Comment comment = commentRepository.findById(cmtNo).orElseThrow(()->new NotExistException("해당 댓글이 존재하지 않습니다."));
		commentRepository.delete(comment);
		
	}

}
