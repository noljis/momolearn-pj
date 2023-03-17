package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicInsert

@Entity
public class Board  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comNo;	//게시글 번호
	
	//Members 객체 단방향 참조
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id")
	private Members members;	//회원id 
	
	@Column(length = 20, nullable = false)
	private String type;	//게시글유형, notice / community
	
	@Column(length = 50, nullable = false)
	private String comTitle;	//글제목
	
	@Column(length = 20, nullable = false)
	private String subject;		//말머리
	
	@CreatedDate
	private LocalDateTime comRegdate;	//작성시간
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String comContent;	//글내용
	
	@Column(length = 6, nullable = false)
	@ColumnDefault("0")
	private Integer comViewCount; //조회수
	
	
	@Builder
	public Board(String type, String comTitle, String subject, String comContent, Members memId) {
		this.type = type;
		this.comTitle = comTitle;
		this.subject = subject;
		this.comContent = comContent;
		this.members = memId;
	}
}