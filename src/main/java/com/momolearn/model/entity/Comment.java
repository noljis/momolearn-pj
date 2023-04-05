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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@ApiModel(value="댓글 정보", description = "커뮤니티 게시글에 할당된 댓글 번호, 회원, 댓글내용, 작성시간 정보")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(example="1")
	private Integer cmtNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "com_no")
	private Board board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id")
	private Members members;
	
	@Column(length = 500, nullable = false)
	@ApiModelProperty(example="댓글 내용입니다.")
	private String cmtContent;
	
	@CreatedDate
	@ApiModelProperty(example="2023-03-26T00:00:00")
	private LocalDateTime cmtRegdate;

	public void update(String cmtContent) {
		this.cmtContent = cmtContent;
	}
}
