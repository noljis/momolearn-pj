package com.momolearn.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@ApiModel(value="회원 정보", description = "회원ID, PW, 이름, Email, 프로필사진, 등급, 가입날짜를 보유한 회원 정보")
public class Members  {
	@Id
	@Column(length = 20, name = "mem_id")
	@ApiModelProperty(example="test01")
	private String memId ;

	@Column(length = 20, nullable = false)
	@ApiModelProperty(example="1234")
	private String pw;
	
	@Column(length = 10, nullable = false)
	@ApiModelProperty(example="학생1")
	private String name;
	
	@Column(length = 100, nullable = false)
	@ApiModelProperty(example="test01@momo.learn")
	private String email;
	
	@Column(length = 255, nullable = false)
	@ColumnDefault("'user.jpg'")
	@ApiModelProperty(example="test01.jpg")
	private String profile;
	
	@Column(length = 20, nullable = false)
	@ColumnDefault("'student'")
	@ApiModelProperty(example="student")
	private String grade;
	
	@Column(length = 6, nullable = false)
	@CreatedDate
	@ApiModelProperty(example="2023-03-26T00:00:00")
	private LocalDateTime regdate;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	public List<MyLectures> myLectures = new ArrayList<>();
	
	@OneToOne(mappedBy = "members", cascade = CascadeType.REMOVE)
	public ApplyTeacher applyTeacher;
	
	@OneToMany(mappedBy = "members" , cascade = CascadeType.REMOVE)
	public List<Board> board = new ArrayList<>();
	
	@OneToMany(mappedBy = "member" , cascade = CascadeType.REMOVE)
	public List<Cart> cart = new ArrayList<>();
	
	public Members(String memId) {
		this.memId = memId;		
	}

}