package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lec_no")
    private Teachers teachers;

    @Column(length = 50)
    private String title;

    @Column(length = 255)
    private String image;

    @Column(length = 6)
    private Integer price;

    @Column(length = 6, nullable = false)
    @ColumnDefault("0")
    private Integer cnt;

    @Column
    @CreationTimestamp
    private LocalDateTime regdate;

    @Column(length = 255)
    private String info;

    @Column(columnDefinition = "TEXT", length = 255)
    private String description;

    @Column(name = "apply_cnt", length = 6)
    private Integer applyCnt;
}