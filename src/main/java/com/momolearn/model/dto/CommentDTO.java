package com.momolearn.model.dto;

import java.time.LocalDateTime;

import com.momolearn.model.entity.Comment;
import com.momolearn.model.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
	private int cmtNo;
	private int boardComNo;
	private Members member;
	private String cmtContent;
	private LocalDateTime cmtRegdate;
	
	public CommentDTO(Comment comment) {
		this.cmtNo = comment.getCmtNo();
		this.boardComNo = comment.getBoard().getComNo();
		this.member = comment.getMembers();
		this.cmtContent = comment.getCmtContent();
		this.cmtRegdate = comment.getCmtRegdate();
	}
}
