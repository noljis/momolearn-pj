package com.momolearn.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDTO  {
	
	private int comNo;
	private String memId;
	private String type;
	private String comTitle;
	private String subject;
	private LocalDateTime comRegdate;
	private String comContent;
	private int comViewCount;
}