package com.momolearn.model.entity;

import javax.persistence.Column;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
@ApiModel(value="강좌 정보", description = "강의에 할당된 강좌번호, 강의, 강좌명, 재생시간, url 정보")
public class Courses  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example="1")
	@Column(name = "course_id")
	
	private Integer courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture;
	
	@Column(length = 50, nullable = false)
	@ApiModelProperty(example="[1강]오리엔테이션")
	private String title;

	@Column(length = 12, nullable = false)
	@ApiModelProperty(example="00:05:24")
	private String time;
	
	@Column(length = 255, nullable = false)
	@ApiModelProperty(example="https://www.youtube.com/embed/QILiHiTD3uc")
	private String url;
	
	public void setLecture(Lectures lecture) {
		
		this.lecture = lecture;
		
	}
	
	public void setTitle(String title) {
		
		this.title = title;
		
	}
	
	public void setUrl(String url) {
		
		this.url = url;
		
	}
	
	public void setTime(String time) {
		
		this.time = time;
		
	}
	
	@Builder
    public Courses(Lectures lecture, String title, String time, String url) {
		
        this.lecture = lecture;
        this.title = title;
        this.time = time;
        this.url = url;
    }
	
}