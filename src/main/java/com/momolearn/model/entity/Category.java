package com.momolearn.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
@ApiModel(value="카테고리", description = "카테고리id, 카테고리명을 보유한 카테고리 정보")
public class Category  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example="1")
	private Integer cateId;
	
	@Column(length = 50, nullable = false)
	@ApiModelProperty(example="java")
	private String cateName;
	
	@OneToMany(mappedBy = "category")
	public List<CategoryLecture> categoryLecture = new ArrayList<>();
	
	public Category(String cateName) {
		
		this.cateName = cateName;
		
	}
	
}