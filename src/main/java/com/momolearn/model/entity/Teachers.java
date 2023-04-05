package com.momolearn.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Builder

@Entity
@ApiModel(value="강사 정보", description = "강사번호, 연락처, 희망분야, 소개, 포트폴리오url, 강사내역 정보")
public class Teachers {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	@ApiModelProperty(example="1")
	private Integer teacherNo;
	
	@Column(name = "phone_num", nullable = false)
	@ApiModelProperty(example="010-1234-5678")
	private String phoneNum;

	@Column(length = 20, nullable = false)
	@ApiModelProperty(example="컴퓨터 사이언스")
	private String hope;
	
	@Column(length = 255, nullable = false)
	@ApiModelProperty(example="안녕하세요 현재 ㅇㅇ대학교 4학년 재학중인 학생입니다.")
	private String intro;
	
	@Column(name = "pf_link", length = 100, nullable = false)
	@ApiModelProperty(example="https://github.com")
	private String pfLink;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_id")
	private ApplyTeacher applyTeacher;
	
	@OneToMany(mappedBy = "teachers" , cascade = CascadeType.REMOVE)
	public List<Lectures> lecture = new ArrayList<>();
	
	public void setTeacherNo(Integer teacherNo) {
		this.teacherNo = teacherNo;
	}
	
}