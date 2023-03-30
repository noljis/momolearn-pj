package com.momolearn.model.dto;

import java.time.LocalDateTime;

import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Members;

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
	private Members member;
	private String type;
	private String comTitle;
	private String subject;
	private LocalDateTime comRegdate;
	private String comContent;
	private int comViewCount;
	
	
	//entity -> DTO
	public BoardDTO(Board board) {
		this.comNo = board.getComNo();
		this.member = board.getMembers();
		this.type = board.getType();
		this.comTitle = board.getComTitle();
		this.subject = board.getSubject();
		this.comRegdate = board.getComRegdate();
		this.comContent = board.getComContent();
		this.comViewCount = board.getComViewCount();
	}

}