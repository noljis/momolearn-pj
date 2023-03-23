package com.momolearn.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
public class Category  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cateId;	//카테고리id
	
	@Column(length = 50, nullable = false)
	private String cateName; //카테고리명
	
	//카테고리수강 테이블과 양방향 매핑. 주테이블
	@OneToMany(mappedBy = "category")
	public List<CategoryLecture> categoryLecture = new ArrayList<>();
	
	
	public Category(String cateName) {
		this.cateName = cateName;
	}
	
}