package com.momolearn.model.entity;

import javax.persistence.CascadeType;
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

@NoArgsConstructor
@AllArgsConstructor
@Getter

@Entity
@SequenceGenerator(
        name="TEACHERS_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="TEACHERS_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class Teachers  {
	
	@Id
	@Column(name = "lec_no", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHERS_SEQ_GEN")
	private Integer lecNo;
	
	@Column(name = "phone_num", length = 20, nullable = false)
	private String phoneNum;

	@Column(length = 20, nullable = false)
	private String hope;
	
	@Column(length = 255, nullable = false)
	private String intro;
	
	@Column(name = "pf_link", length = 100, nullable = false)
	private String pfLink;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "members_id")
	private Members members;
	
	

}