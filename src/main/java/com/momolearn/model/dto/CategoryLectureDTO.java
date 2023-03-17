package com.momolearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryLectureDTO  {
	
	private int cateLecId;	//카테고리강의ID
	private int lectureId;	//강의ID
	private int categoryId; //카테고리ID

}
