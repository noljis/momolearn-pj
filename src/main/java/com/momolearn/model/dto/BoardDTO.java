package com.momolearn.model.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.momolearn.model.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDTO  {
	
	private int comNo;
	private String membersMemId;
	private String type;
	private String comTitle;
	private String subject;
	private LocalDateTime comRegdate;
	private String comContent;
	private int comViewCount;
	
	
	public Page<BoardDTO> toDtoPage(Page<Board> entityPage){
		Page<BoardDTO> dtoPage = entityPage.map(m->BoardDTO.builder()
				.comNo(m.getComNo())
				.membersMemId(m.getMembers().getMemId())
				.type(m.getType())
				.comTitle(m.getComTitle())
				.subject(m.getSubject())
				.comRegdate(m.getComRegdate())
				.comViewCount(m.getComViewCount())
				.build());
		return dtoPage;
	}

}