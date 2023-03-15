package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@SequenceGenerator(name = "MYLECTURES_SEQ_GEN", // 시퀀스 제너레이터 이름
sequenceName = "MYLECTURES_SEQ", // 시퀀스 이름
initialValue = 1, // 시작값
allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
public class MyLectures  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MYLECTURES_SEQ_GEN")
	@Column(name = "mylec_id")
	private Integer mylecId;	//카테고리id
	
	//lectures 테이블과 다대일 단방향
	@ManyToOne
    @JoinColumn(name = "member_id")
	private Members member; //학생id
	
	//lectures 테이블과 다대일 단방향
	@ManyToOne
    @JoinColumn(name = "lecture_id")
	private Lectures lecture; //강의id
	
	@Column(name="cate_name")
	@CreationTimestamp
	private LocalDateTime cateName; //수강신청일

}
