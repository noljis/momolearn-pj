package com.momolearn.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert

@Entity
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="강의 정보", description = "강사에 할당된 강의번호, 강사, 강의명, 썸네일, 가격, 강좌수, 수강생수, 등록일, 설명 정보")
public class Lectures  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example="1")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_no")
    private Teachers teachers;

    @Column(length = 50, nullable = false)
    @ApiModelProperty(example="자바의 정석")
    private String title;
    
    @Column(length = 255, nullable = false)
    @ColumnDefault("'default.jpg'")
    @ApiModelProperty(example="자바의 정석.png")
    private String image; 

    @Column(length = 6, nullable = false)
    @ApiModelProperty(example="5000")
    private Integer price;

    @Column(length = 6, nullable = false)
    @ColumnDefault("0")
    @ApiModelProperty(example="10")
    private Integer cnt;

    @Column
    @CreatedDate
    @ApiModelProperty(example="2023-03-26T00:00:00")
    private LocalDateTime regdate;

    @Column(length = 255, nullable = false)
    @ApiModelProperty(example="자바 입문강의")
    private String info;

    @Column(columnDefinition = "TEXT", nullable = false)
    @ApiModelProperty(example="자바의 역사부터 실전까지 차근차근 알려드립니다.")
    private String description;

    @Column(length = 6, nullable = false)
    @ApiModelProperty(example="10")
    private Integer applyCnt;
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<Courses> courses = new ArrayList<>();
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<MyLectures> mylectures = new ArrayList<>();
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<Cart> cart = new ArrayList<>();
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<CategoryLecture> categoryLecture = new ArrayList<>();
    
}