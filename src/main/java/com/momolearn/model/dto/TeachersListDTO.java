package com.momolearn.model.dto;

import com.momolearn.model.entity.Lectures;
import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.Teachers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeachersListDTO  {
	
	private int teacherNo;
	private Members member;
//	private String profile;
//	private String name;
	private String hope;
	private String pfLink;
	private Lectures lectures;
	
	public TeachersListDTO(Teachers teachers) {
		this.teacherNo = teachers.getTeacherNo();
		this.member = teachers.getApplyTeacher().getMembers();
//		this.profile = teachers.getApplyTeacher().getMembers().getProfile();
//		this.name = teachers.getApplyTeacher().getMembers().getName();
		this.hope = teachers.getHope();
		this.pfLink = teachers.getPfLink();
		this.lectures = teachers.getLecture().get(teacherNo);
	}
}