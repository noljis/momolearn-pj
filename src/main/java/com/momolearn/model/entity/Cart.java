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
public class Cart  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Integer cartId;	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
	private Members member; //학생id
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
	private Lectures lecture; //강의id
	
	@Builder
	public Cart(Members member, Lectures lecture) {
		this.member = member;
		this.lecture = lecture;
	}
	
}