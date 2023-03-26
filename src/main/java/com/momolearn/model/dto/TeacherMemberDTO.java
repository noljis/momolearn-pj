package com.momolearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherMemberDTO {
	
	private String name; //회원이름
	private int teacherNo; //강사 번호
	
}