package com.momolearn.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.Teachers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@JsonSerialize
@JsonDeserialize
public class TeachersListDTO {
    private Integer teachersNo;
    private Members members;
    private String name;
    private String profile;

	
    public TeachersListDTO(Teachers teachers) {
        this.teachersNo = teachers.getTeacherNo();
        this.members = teachers.getApplyTeacher().getMembers();
        this.name = teachers.getApplyTeacher().getMembers().getName();
        this.profile = teachers.getApplyTeacher().getMembers().getProfile();
    }

}
