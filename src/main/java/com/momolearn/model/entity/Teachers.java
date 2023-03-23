package com.momolearn.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
//ApplyTeacher를 참조하고 있는데 연락처, 희망분야, 자기소개 ,포폴링크 속성이 필요할까?
public class Teachers {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Integer teacherNo;	//강사번호
	
	@Column(name = "phone_num", nullable = false)
	private String phoneNum;	//연락처

	@Column(length = 20, nullable = false)
	private String hope; //희망분야
	
	@Column(length = 255, nullable = false)
	private String intro;	//자기소개 수정할 수 있는 부분
	
	@Column(name = "pf_link", length = 100, nullable = false)
	private String pfLink;	//포폴링크
	
	//양방향 연관관계의 주인. 대상테이블에 외래키가 존재 
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_id")
	private ApplyTeacher applyTeacher;
	
	public void setTeacherNo(Integer teacherNo) {
		this.teacherNo = teacherNo;
	}
	
}