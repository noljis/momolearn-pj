package com.momolearn.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
public class Courses  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Integer courseId;	//강좌id
	
	//Lectures테이블 양방향연관관계 주인
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture; //강의id <- lectures
	
	@Column(length = 50, nullable = false)
	private String title; //강좌명

	@Column(length = 12, nullable = false)
	private String time; //강좌 시간
	
	@Column(length = 255, nullable = false)
	private String url; //유튜브url
	
	//양방향 setter 지정
	public void setLecture(Lectures lecture) {
		this.lecture = lecture;
	}
	
	@Builder
    public Courses(Lectures lecture, String title, String time, String url) {
        this.lecture = lecture;
        this.title = title;
        this.time = time;
        this.url = url;
    }
	

}