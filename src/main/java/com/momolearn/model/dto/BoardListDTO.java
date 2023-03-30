package com.momolearn.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Comment;
import com.momolearn.model.entity.Likes;
import com.momolearn.model.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListDTO {
	private int comNo;
	private Members member;
	private String type;
	private String comTitle;
	private String subject;
	private LocalDateTime comRegdate;
	private String comContent;
	private int comViewCount;
	private List<Comment> comments;
	private List<Likes> likes;
	
	public BoardListDTO(Board board) {
		this.comNo = board.getComNo();
		this.member = board.getMembers();
		this.type = board.getType();
		this.comTitle = board.getComTitle();
		this.subject = board.getSubject();
		this.comRegdate = board.getComRegdate();
		this.comContent = board.getComContent();
		this.comViewCount = board.getComViewCount();
		this.comments = board.getComments();
		this.likes = board.getLikes();
	}
}
