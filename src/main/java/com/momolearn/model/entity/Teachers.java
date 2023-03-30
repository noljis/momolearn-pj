package com.momolearn.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Builder

@Entity
public class Teachers {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Integer teacherNo;
	
	@Column(name = "phone_num", nullable = false)
	private String phoneNum;

	@Column(length = 20, nullable = false)
	private String hope;
	
	@Column(length = 255, nullable = false)
	private String intro;
	
	@Column(name = "pf_link", length = 100, nullable = false)
	private String pfLink;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_id")
	private ApplyTeacher applyTeacher;
	
	@OneToMany(mappedBy = "teachers" , cascade = CascadeType.REMOVE)
	public List<Lectures> lecture = new ArrayList<>();
	
	public void setTeacherNo(Integer teacherNo) {
		this.teacherNo = teacherNo;
	}
	
}