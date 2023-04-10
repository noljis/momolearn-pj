package com.momolearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyLecturesTeacherDTO  {
	
	private Integer mylecId;
	
	private MembersDTO member;
	
	private LecturesDTO lecture;
	
	private TeachersDTO teachers; 
	
	private MembersDTO lectureTeachersApplyTeacherMembers;

}
