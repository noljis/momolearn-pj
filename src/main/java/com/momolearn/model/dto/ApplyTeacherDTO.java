package com.momolearn.model.dto;

import java.time.LocalDateTime;

import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyTeacherDTO {

    private int id;
    private String membersMemId;
    private String phoneNum;
    private String hopeFiled;
    private String intro;
    private String pfLink;
    private LocalDateTime applyRege;
    private String approve;
    
    public ApplyTeacher toEntity(Members members) {
    	return ApplyTeacher.builder()
    			//.members(members)
    			.phoneNum(phoneNum)
    			.hopeFiled(hopeFiled)
    			.pfLink(pfLink)
    			.intro(intro)
    			.approve(approve)
    			.build();
    }
    
    
	public ApplyTeacher toEntity(ApplyTeacherDTO dto) {
		return ApplyTeacher.builder()
				.id(dto.getId())
				.phoneNum(dto.getPhoneNum())
				.hopeFiled(dto.getHopeFiled())
				.intro(dto.getIntro())
				.pfLink(dto.getPfLink())
				.approve(dto.getApprove())
				.members(new Members(dto.getMembersMemId()))
				.build();
	}

}