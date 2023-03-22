package com.momolearn.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
public class CategoryLecture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cateLecId;	//카테고리id
	
	//lectures 테이블과 다대일 단방향. 양방향이 될 경우 연관관계의 주인이 됨
	//중간테이블을 불러왔을때 강의 조회: 단방향
	//강의 조회시 카테고리 조회: 양방향
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture; //강의id
	
	//Category 테이블과 다대일 단방향. 양방향이 될 경우 연관관계의 주인이 됨
	//java 조회했을때 CategoryLecture을 조회 양방향
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category; //카테고리id
	
	//양방향이 필요할 경우 setter 작성
	public void setLecture(Lectures lecture) {
		this.lecture = lecture;
	}
	
	public CategoryLecture(Lectures lecture, Category category) {
	    this.lecture = lecture;
	    this.category = category;
	}

}