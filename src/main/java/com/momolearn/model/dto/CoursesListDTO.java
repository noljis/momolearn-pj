package com.momolearn.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesListDTO {

	private List<Integer> lectureId = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private List<String> time = new ArrayList<>();
    private List<String> url = new ArrayList<>();

}