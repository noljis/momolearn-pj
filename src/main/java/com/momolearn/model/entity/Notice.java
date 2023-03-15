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
public class Notice  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeNo;
	
	@Column(length = 50, nullable = false)
	private String noTitle;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String noContent;
	
	@CreatedDate
	private LocalDateTime noRegdate;
	
	@Column(length = 6, nullable = false)
	@ColumnDefault("0")
	private Integer noViewCount;
	
	@ManyToOne
	@JoinColumn(name = "adminId")
	private Members members;
	
	@Builder
	public Notice(String noTitle, String noContent, Members adminId) {
		this.noTitle = noTitle;
		this.noContent = noContent;
		this.members = adminId;
	}
}