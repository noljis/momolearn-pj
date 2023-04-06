package com.momolearn.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MembersDTO {
	
	private String memId;
	
	private String pw;
	
	private String name;
	
	private String email;
	
	private String profile;
	
	private String grade;
	
	private LocalDateTime regdate;
	
}