package com.momolearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesDTO {

    private int courseId;
    
    private int lectureId;
    
    private String title;
    
    private String time;
    
    private String url;

}