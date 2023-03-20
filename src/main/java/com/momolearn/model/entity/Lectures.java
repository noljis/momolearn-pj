package com.momolearn.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicInsert

@Entity
public class Lectures  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //강의id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_no")
    private Teachers teachers; //강사id <- teachers

    @Column(length = 50, nullable = false)
    private String title; //강의제목

    @Column(length = 255, nullable = false)
    @ColumnDefault("'default.jpg'")
    private String image; //강의썸네일 default null not null 차이점

    @Column(length = 6, nullable = false)
    private Integer price; //강의가격

    @Column(length = 6, nullable = false)
    @ColumnDefault("0")
    private Integer cnt; //강좌수

    @Column
    @CreatedDate
    private LocalDateTime regdate; //강의등록일

    @Column(length = 255, nullable = false)
    private String info; //강의한줄설명

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description; //강의상세설명

    @Column(length = 6, nullable = false)
    private Integer applyCnt; //수강학생수
    
    //강의 조회시 강좌도 조회 양방향 주테이블
    @OneToMany(mappedBy = "lecture")
    private List<Courses> courses = new ArrayList<>(); //강의 -> 강좌
    
    //강의 조회시 카테고리도 조회 양방향 주테이블
    @OneToMany(mappedBy = "lecture")
    private List<CategoryLecture> categoryLecture = new ArrayList<>();
    
}