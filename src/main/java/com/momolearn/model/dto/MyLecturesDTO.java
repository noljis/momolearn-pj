package com.momolearn.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyLecturesDTO  {
	
	private Integer mylecId;
	private String memberId;
	private int lectureId;
	private LocalDateTime lecRege;

}
