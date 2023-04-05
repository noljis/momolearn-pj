package com.momolearn.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
@ApiModel(value="카테고리-강의 정보", description = "강의에 할당된 카테고리들 정보")
public class CategoryLecture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example="1")
	private Integer cateLecId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	public void setLecture(Lectures lecture) {
		this.lecture = lecture;
	}
	
	public CategoryLecture(Lectures lecture, Category category) {
	    this.lecture = lecture;
	    this.category = category;
	}

}