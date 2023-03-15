package com.momolearn.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Entity
public class ApplyTeacher  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "apply_id")
    private Members members;

    @Column(name = "phone_num", length = 20)
    private String phoneNum;

    @Column(name = "hope_filed", length = 20)
    private String hopeFiled;

    @Column(length = 255)
    private String intro;

    @Column(name = "pf_link", length = 100)
    private String pfLink;

    @Column(name = "apply_rege")
    @CreationTimestamp
    private LocalDateTime applyRege;
}