package com.momolearn.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Board  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id")
	private Members members;
	
	@Column(length = 20, nullable = false)
	private String type;
	
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
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	@OrderBy("cmt_no asc")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Likes> likes;
	
	
	
	
	@Builder
	public Board(String type, String comTitle, String subject, String comContent, Members membersMemId, Integer comViewCount) {
		this.type = type;
		this.comTitle = comTitle;
		this.subject = subject;
		this.comContent = comContent;
		this.members = membersMemId;
		this.comViewCount = comViewCount;
	}

	
	
	public void update(String comTitle, String subject, String comContent) {
		this.comTitle = comTitle;
		this.subject = subject;
		this.comContent = comContent;
		
	}
	
}