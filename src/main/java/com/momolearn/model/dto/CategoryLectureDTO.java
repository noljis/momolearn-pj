package com.momolearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryLectureDTO  {
	
	private int cateLecId;
	private int lectureId;
	private int categoryId;

}
