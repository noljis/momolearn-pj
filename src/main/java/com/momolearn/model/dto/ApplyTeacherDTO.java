package com.momolearn.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyTeacherDTO {

    private int id;
    private String applyId;
    private String phoneNum;
    private String hopeFiled;
    private String intro;
    private String pfLink;
    private LocalDateTime applyRege;
    private String approve;

}