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
@Entity
public class Members  {
	@Id
	@Column(length = 20, name = "mem_id")
	private String memId ;

	@Column(length = 20, nullable = false)
	private String pw;
	
	@Column(length = 10, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@Column(length = 255, nullable = false)
	@ColumnDefault("'user.jpg'")
	private String profile;
	
	@Column(length = 20, nullable = false)
	@ColumnDefault("'student'")
	private String grade;
	
	@Column(length = 6, nullable = false)
	@CreatedDate
	private LocalDateTime regdate;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	public List<MyLectures> myLectures = new ArrayList<>();
	
	@OneToOne(mappedBy = "members", cascade = CascadeType.REMOVE)
	public ApplyTeacher applyTeacher;
	
	@OneToMany(mappedBy = "members" , cascade = CascadeType.REMOVE)
	public List<Board> board = new ArrayList<>();
	
	@OneToMany(mappedBy = "members" , cascade = CascadeType.REMOVE)
	public List<Comment> comment = new ArrayList<>();
	
	@OneToMany(mappedBy = "members" , cascade = CascadeType.REMOVE)
	public List<Likes> like = new ArrayList<>();
	
	@OneToMany(mappedBy = "member" , cascade = CascadeType.REMOVE)
	public List<Cart> cart = new ArrayList<>();
	
	public Members(String memId) {
		this.memId = memId;		
	}
}