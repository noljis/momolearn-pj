package com.momolearn.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureCoursesDTO {

    private int id;
    private int teachersTeacherNo; //매핑컬럼
    private String title;
    private String image;
    private int price;
    private int cnt;
    private LocalDateTime regdate;
    private String info;
    private String description;
    private int applyCnt;
    private List<CoursesDTO> courses;
    private MembersDTO teachersApplyTeacherMembers;

}