package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicInsert

@Entity
public class Members  {
	@Id
	@Column(length = 20, nullable = false)
	private String memId ;

	@Column(length = 20, nullable = false)
	private String pw;
	
	@Column(length = 10, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@Column(length = 255, nullable = false)
	@ColumnDefault("'user.jpg'")
	private String profile; //default : /img/profile/user.jpg
	
	@Column(length = 20, nullable = false)
	@ColumnDefault("'student'")
	private String grade; //default : student
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime regdate;
	
	
}