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
public class Lectures  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_no")
    private Teachers teachers;

    @Column(length = 50, nullable = false)
    private String title;
    
    @Column(length = 255, nullable = false)
    @ColumnDefault("'default.jpg'")
    private String image; 

    @Column(length = 6, nullable = false)
    private Integer price;

    @Column(length = 6, nullable = false)
    @ColumnDefault("0")
    private Integer cnt;

    @Column
    @CreatedDate
    private LocalDateTime regdate;

    @Column(length = 255, nullable = false)
    private String info;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 6, nullable = false)
    private Integer applyCnt;
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<Courses> courses = new ArrayList<>(); //강의 -> 강좌
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<MyLectures> mylectures = new ArrayList<>(); //강의 -> 강좌
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<Cart> cart = new ArrayList<>(); //강의 -> 강좌
    
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    private List<CategoryLecture> categoryLecture = new ArrayList<>();
    
}