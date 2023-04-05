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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate

@Builder

@EntityListeners(AuditingEntityListener.class)
@Entity
@ApiModel(value="강사 신청서 정보", description = "회원ID, 연락처, 희망분야, 소개, 포트폴리오url, 승인여부 정보를 보유한 강사 내역 정보")
public class ApplyTeacher  {	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example="1")
    private Integer id; 

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private Members members; 

    @Column(length = 20, nullable = false)
    @ApiModelProperty(example = "010-1234-5678")
    private String phoneNum;

    @Column(length = 20, nullable = false, name="hope_field")    
    @ApiModelProperty(example = "컴퓨터 사이언스")
    private String hopeField;

    @Column(length = 255, nullable = false)
    @ApiModelProperty(example = "자기소개")
    private String intro;

    @Column(length = 100, nullable = false)
    @ApiModelProperty(example = "포트폴리오 url")
    private String pfLink;

    @CreationTimestamp
    @ApiModelProperty(example = "2023-03-26T00:00:00")
    private LocalDateTime applyRege; 
    
    @Column(length = 15)
    @ColumnDefault("'false'")
    @ApiModelProperty(example = "false")
    private String approve; 
    
    @OneToOne(mappedBy = "applyTeacher", cascade = CascadeType.REMOVE)
    public Teachers teacher;
    
	public ApplyTeacher(int id) {
		this.id = id;
	}

	public void setApplyForm(String phoneNum, String hopeField, String pfLink, String intro) {
		this.phoneNum = phoneNum;
		this.hopeField = hopeField;
		this.pfLink = pfLink;
		this.intro = intro;
	}
    
}


