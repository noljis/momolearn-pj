package com.momolearn.model.dto;

import com.momolearn.model.entity.Lectures;
import com.momolearn.model.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO  {
	
	private int cartId;
	private Members member;
	private Lectures lecture;
}