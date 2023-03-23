package com.momolearn.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicInsert

@ToString
@Entity
public class Members  {
	@Id
	@Column(length = 20, name = "mem_id")
	private String memId ; //회원id

	@Column(length = 20, nullable = false)
	private String pw; //회원비밀번호
	
	@Column(length = 10, nullable = false)
	private String name; //회원이름
	
	@Column(length = 100, nullable = false)
	private String email; //이메일
	
	@Column(length = 255, nullable = false)
	@ColumnDefault("'user.jpg'")
	private String profile; //default : /img/profile/user.jpg
	
	@Column(length = 20, nullable = false)
	@ColumnDefault("'student'")
	private String grade; //default : student
	
	@Column(length = 6, nullable = false)
	@CreatedDate
	private LocalDateTime regdate;	//가입일
	
	//MyLectures과 다대일 양방향 주테이블
	@OneToMany(mappedBy = "member")
	public List<MyLectures> myLectures = new ArrayList<>();
	
	//ApplyTeacher과 1:1 양방향. 주테이블
	@OneToOne(mappedBy = "members")
	public ApplyTeacher applyTeacher;
	
	public Members(String memId) {
		this.memId = memId;		
	}
}