package com.momolearn.model.dto;

import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Teachers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeachersDTO  {
	
	private int teacherNo; //강사번호
	private String phoneNum; //연락처
	private String hope; //희망분야
	private String intro; //소개글
	private String pfLink; //포폴링크
	private int applyId; //강사신청폼 id


	public static Teachers toEntity(ApplyTeacherDTO applyDTO) {
		return Teachers.builder()
				.phoneNum(applyDTO.getPhoneNum())
				.pfLink(applyDTO.getPfLink())
				.hope(applyDTO.getHopeField())
				.intro(applyDTO.getIntro())
				.applyTeacher(new ApplyTeacher(applyDTO.getId()))
				.build();
	}
}