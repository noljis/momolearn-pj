package com.momolearn.model.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.Teachers;

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
    private String hopeField;
    private String intro;
    private String pfLink;
    private LocalDateTime applyRege;
    private String approve;
    
    
    public ApplyTeacher toEntity(ApplyTeacherDTO applyDTO) {
    	return ApplyTeacher.builder()
    			.id(applyDTO.getId())
    			.phoneNum(applyDTO.getPhoneNum())
    			.pfLink(applyDTO.getPfLink())
    			.hopeField(applyDTO.getHopeField())
    			.intro(applyDTO.getIntro())
    			.approve(applyDTO.getApprove())
    			.applyRege(applyDTO.getApplyRege())
    			.members(new Members(applyDTO.getMembersMemId()))
    			.build();
    }
    
    
    
    
    public ApplyTeacher toEntity(Members members) {
    	return ApplyTeacher.builder()
    			//.members(members)
    			.phoneNum(phoneNum)
    			.hopeField(hopeField)
    			.pfLink(pfLink)
    			.intro(intro)
    			.approve(approve)
    			.build();
    }
    
    

}