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
public class MyLectures  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mylecId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
	private Members member;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture;
	
	@CreatedDate
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
