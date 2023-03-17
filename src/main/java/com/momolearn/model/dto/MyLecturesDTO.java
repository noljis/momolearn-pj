package com.momolearn.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyLecturesDTO  {
	
	private Integer mylecId;	//수강id
	private String memberId; //학생id
	private int lectureId; //강의id
	private LocalDateTime lecRege; //수강신청일

}
