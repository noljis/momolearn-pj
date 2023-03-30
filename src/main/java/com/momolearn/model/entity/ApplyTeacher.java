package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicInsert
@DynamicUpdate

@Builder

@EntityListeners(AuditingEntityListener.class)
@Entity
public class ApplyTeacher  {	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private Members members; 

    @Column(length = 20, nullable = false)
    private String phoneNum;

    @Column(length = 20, nullable = false, name="hope_field")    
    private String hopeField;

    @Column(length = 255, nullable = false)
    private String intro;

    @Column(length = 100, nullable = false)
    private String pfLink;

    @CreationTimestamp
    private LocalDateTime applyRege; 
    
    @Column(length = 15)
    @ColumnDefault("'false'")
    private String approve; 
    
    @OneToOne(mappedBy = "applyTeacher", cascade = CascadeType.REMOVE)
    public Teachers teacher;
    
    public void setApprove(String approve) {
        this.approve = approve;
    }

    public void setApplyForm(String phoneNum, String hopeField, String pfLink, String intro) {
    	this.phoneNum = phoneNum;
		this.hopeField = hopeField;
		this.intro = intro;
		this.pfLink = pfLink;
    }

	public ApplyTeacher(int id) {
		this.id = id;
	}
    
//	public void setPhoneNum(String phoneNum) {
//		this.phoneNum = phoneNum;
//	}
//
//	public void setHopeField(String hopeField) {
//		this.hopeField = hopeField;
//	}
//
//	public void setIntro(String intro) {
//		this.intro = intro;
//	}
//
//	public void setPfLink(String pfLink) {
//		this.pfLink = pfLink;
//	}

}


