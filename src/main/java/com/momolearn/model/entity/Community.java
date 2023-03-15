package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert

@Entity
public class Community  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comNo;
	
	@Column(length = 50, nullable = false)
	private String comTitle;
	
	@Column(length = 20, nullable = false)
	private String subject;
	
	@CreatedDate
	private LocalDateTime comRegdate;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String comContent;
	
	@Column(length = 6, nullable = false)
	@ColumnDefault("0")
	private Integer comViewCount;
	
	@ManyToOne
	@JoinColumn(name = "memId")
	private Members members;
	
	@Builder
	public Community(String comTitle, String subject, String comContent, Members memId) {
		this.comTitle = comTitle;
		this.subject = subject;
		this.comContent = comContent;
		this.members = memId;
	}
}