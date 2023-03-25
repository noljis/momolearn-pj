package com.momolearn.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApplyTeacherDTOList {

	private List<ApplyTeacherDTO> list;
	
	public ApplyTeacherDTOList() {
		list = new ArrayList<>();
	}

}