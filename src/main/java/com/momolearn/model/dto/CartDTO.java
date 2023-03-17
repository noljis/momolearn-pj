package com.momolearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO  {
	
	private int cartId;
	private String memberId;
	private int lectureId;
}