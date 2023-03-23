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
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicInsert

@Builder

@EntityListeners(AuditingEntityListener.class)
@Entity
public class ApplyTeacher  {	//강사신청 테이블. 회원한명당 하나의 강사신청을 할 수 있다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //신청폼 id

    //ApplyTeacher 양방향 연관관계의 주인 / 대상객체 - 외래키 존재
    @OneToOne(fetch = FetchType.LAZY)	//fetchType.LAZY :OneToOne에서 LAZYloading발생하는 이슈 확인
    @JoinColumn(name = "apply_id")
    private Members members; //신청자id <-> members

    @Column(length = 20, nullable = false)
    private String phoneNum; //연락처

    @Column(length = 20, nullable = false)
    private String hopeFiled; //희망분야

    @Column(length = 255, nullable = false)
    private String intro; //소개글

    @Column(length = 100, nullable = false)
    private String pfLink; //포트폴리오링크

    @CreationTimestamp
    private LocalDateTime applyRege; //신청일자
    
    //승인여부 - true/false 이므로 boolean으로 반환해서 받아오면..
    //적용방법을 좀 더 공부해서 boolean으로 할지 정하기
    @Column(length = 15, nullable = false)
    @ColumnDefault("'false'")	//기본값 false, 관리자가 승인시 true로 변경
    private String approve; 
    
    //Teachers 테이블과 1:1양방향. 주테이블
    @OneToOne(mappedBy = "applyTeacher")
    public Teachers teacher;
}


