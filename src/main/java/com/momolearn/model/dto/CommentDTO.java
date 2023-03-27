package com.momolearn.model.dto;

import java.time.LocalDateTime;

import com.momolearn.model.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
	private int cmtNo;
	private int boardComNo;
	private String membersMemId;
	private String cmtContent;
	private LocalDateTime cmtRegdate;
	
	//entity -> DTO
	public CommentDTO(Comment comment) {
		this.cmtNo = comment.getCmtNo();
		this.boardComNo = comment.getBoard().getComNo();
		this.membersMemId = comment.getMembers().getMemId();
		this.cmtContent = comment.getCmtContent();
		this.cmtRegdate = comment.getCmtRegdate();
		
	}
	
}
