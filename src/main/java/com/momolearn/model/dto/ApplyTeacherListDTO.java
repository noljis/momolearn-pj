package com.momolearn.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyTeacherListDTO {

	private List<ApplyTeacherDTO> list = new ArrayList<>();
	
}