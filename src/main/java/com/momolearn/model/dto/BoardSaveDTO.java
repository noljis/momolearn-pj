package com.momolearn.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardSaveDTO {
	
	private String membersMemId;
	private String type;
	@NotBlank(message = "제목을 입력하세요.")
	private String comTitle;
	@NotBlank(message = "카테고리를 선택하세요.")
	private String subject;
	
	private LocalDateTime comRegdate;
	@NotBlank(message = "내용을 입력하세요.")
	private String comContent;
	private int comViewCount;
	
	public Board toEntity(Members members) {
		return Board.builder()
				.type(type)
				.membersMemId(members)
				.comTitle(comTitle)
				.subject(subject)
				.comContent(comContent)
				.comViewCount(comViewCount)
				.build();
	}
}
