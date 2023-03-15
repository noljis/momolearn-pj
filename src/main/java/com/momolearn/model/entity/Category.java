package com.momolearn.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@SequenceGenerator(name = "CATEGORY_SEQ_GEN", // 시퀀스 제너레이터 이름
sequenceName = "CATEGORY_SEQ", // 시퀀스 이름
initialValue = 1, // 시작값
allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
public class Category  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_GEN")
	@Column(name = "cate_id")
	private Integer cateId;	//카테고리id
	
	//lectures 테이블과 다대일 단방향
	@ManyToOne
    @JoinColumn(name = "lecture_id")
	private Lectures lecture; //강의id
	
	@Column(length = 50, nullable = false)
	private String cateName; //카테고리명

}
