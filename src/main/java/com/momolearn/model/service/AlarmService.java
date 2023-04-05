package com.momolearn.model.service;

import static com.momolearn.controller.SseController.sseEmitters;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.momolearn.model.BoardRepository;
import com.momolearn.model.entity.Board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlarmService {
	
	private final BoardRepository boardRepository;
	
	public void writeCommentEvent(int comNo) {
		Board board = boardRepository.findById(comNo).orElseThrow(()->new IllegalArgumentException("찾을 수 없는 게시글입니다."));
		String userId = board.getMembers().getMemId();
		
		if(sseEmitters.containsKey(userId)) {
			SseEmitter sseEmitter = sseEmitters.get(userId);
			try {
				sseEmitter.send(SseEmitter.event().name("writeComment").data("🔔작성한 글에 댓글이 달렸습니다"));
			}catch(Exception e) {
				sseEmitters.remove(userId);
			}
		}
	}

	public void likePostEvent(int comNo) {
		Board board = boardRepository.findById(comNo).orElseThrow(()->new IllegalArgumentException("찾을 수 없는 게시글입니다."));
		String userId = board.getMembers().getMemId();
		
		if(sseEmitters.containsKey(userId)) {
			SseEmitter sseEmitter = sseEmitters.get(userId);
			try {
				sseEmitter.send(SseEmitter.event().name("likePost").data("💕작성한 글에 좋아요가 올라갔습니다"));
			}catch(Exception e) {
				sseEmitters.remove(userId);
			}
		}
	}
}
