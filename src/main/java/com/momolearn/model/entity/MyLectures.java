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
	private Integer mylecId;	//수강id
	
	//Members 객체와 다대일 단방향. 양방향이 될 경우 연관관계의 주인이 됨
	//수강중인 강의를 불러올때 회원객체 정보 가져옴: 단방향
	//회원을 불러올때 수강중인 강의 정보 가져옴: 양방향
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
	private Members member; //학생id
	
	//Lectures 객체와 다대일 단방향. 양방향이 될 경우 연관관계의 주인이 됨
	//수강중인 강의를 불러올때 강의 정보까지 한꺼번에 불러오기: 단방향
	//강의를 불러올때 해당강의를 수강중인 학생 정보까지 한꺼번에 조회: 양방향.. 좀 더 생각해보기
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture; //강의id
	
	@CreatedDate
	private LocalDateTime lecRege; //수강신청일
	
	//양방향일 경우 setter작성
	//member와 양방향
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
