package com.momolearn.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	//id로 강사 한명 조회
	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException{
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemId(id).orElseThrow(() -> new NotExistException("현재 존재하지 않는 강사등급입니다."));
		
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
		
	}

	public Object getApplyTeacherList() {
		return null;
	}
	
	

}
