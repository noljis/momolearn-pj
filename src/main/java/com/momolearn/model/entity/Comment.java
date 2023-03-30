package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString//임시
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cmtNo;
	
	@ManyToOne
	@JoinColumn(name = "com_no")
	private Board board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id")
	private Members members;
	
	@Column(length = 500, nullable = false)
	private String cmtContent;
	
	@CreatedDate
	private LocalDateTime cmtRegdate;

	public void update(String cmtContent) {
		this.cmtContent = cmtContent;
	}
}
