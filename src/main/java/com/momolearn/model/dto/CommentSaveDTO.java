package com.momolearn.model.dto;

import java.time.LocalDateTime;

import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Comment;
import com.momolearn.model.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentSaveDTO {
	private int cmtNo;
	private Board board;
	private Members members;
	private String cmtContent;
	private LocalDateTime cmtRegdate;
	
	
	public Comment toEntity() {
		Comment comment = Comment.builder()
				.cmtNo(cmtNo)
				.board(board)
				.members(members)
				.cmtContent(cmtContent)
				.cmtRegdate(cmtRegdate)
				.build();
		return comment;
	}
}
