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