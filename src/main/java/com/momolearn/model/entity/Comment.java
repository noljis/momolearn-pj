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
	private Integer cmtNo; //댓글 번호
	
	@ManyToOne
	@JoinColumn(name = "com_no")
	private Board board; //게시글번호
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id")
	private Members members; //회원id
	
	@Column(length = 500, nullable = false)
	private String cmtContent; //댓글 내용
	
	@CreatedDate
	private LocalDateTime cmtRegdate; //작성시간

	public void update(String cmtContent) {
		this.cmtContent = cmtContent;
	}
}
