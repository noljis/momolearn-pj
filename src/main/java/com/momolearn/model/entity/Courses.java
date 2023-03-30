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
	private Integer courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture;
	
	@Column(length = 50, nullable = false)
	private String title;

	@Column(length = 12, nullable = false)
	private String time;
	
	@Column(length = 255, nullable = false)
	private String url;
	
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