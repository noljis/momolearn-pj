package com.momolearn.model.dto;

import java.time.LocalDateTime;

import com.momolearn.model.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HitBoardDTO {
	
	private int comNo;
	
	private String membersMemId;
	
	private String type;
	
	private String comTitle;
	
	private String subject;
	
	private LocalDateTime comRegdate;
	
	private String comContent;
	
	private int comViewCount;
	
	public HitBoardDTO(Board board) {
		
		this.comNo = board.getComNo();
		this.membersMemId = board.getMembers().getMemId();
		this.type = board.getType();
		this.comTitle = board.getComTitle();
		this.subject = board.getSubject();
		this.comRegdate = board.getComRegdate();
		this.comContent = board.getComContent();
		this.comViewCount = board.getComViewCount();
		
	}
	
}
