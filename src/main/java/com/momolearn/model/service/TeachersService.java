package com.momolearn.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.momolearn.model.TeachersRepository;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.entity.ApplyTeacher;


@Service
public class TeachersService {
	
	@Autowired
	private TeachersRepository teachersRepository;
	
	@Autowired
	private ApplyTeacherRepository applyTeacherRepository;
	
	private ModelMapper mapper = new ModelMapper();

	//id와 승인여부로 강사 한명 조회
	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException{
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemIdAndApprove(id).orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
		
	}
	
	public Object getApplyTeacherList() {
		return null;
	}
	
	
	
}
