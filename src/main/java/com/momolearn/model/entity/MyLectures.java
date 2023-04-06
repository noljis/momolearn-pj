package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicInsert

@EntityListeners(AuditingEntityListener.class)
@Entity
@ApiModel(value="수강중인 강의 정보", description = "회원별로 수강중인 강의 정보와 수강날짜 정보")
public class MyLectures  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example="1")
	private Integer mylecId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
	private Members member;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture;
	
	@CreatedDate
	@ApiModelProperty(example="2023-03-26T00:00:00")
	private LocalDateTime lecRege;
	
	public void setMember(Members member) {
		
		this.member = member;
		
	}
	
	public void setLecture(Lectures lecture) {
		
		this.lecture = lecture;
		
	}
	
	@Builder
	public MyLectures(Members member, Lectures lecture) {
		
		this.member = member;
		this.lecture = lecture;
		
	}

}
