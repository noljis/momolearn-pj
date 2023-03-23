package com.momolearn.model.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Teachers;


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

	// 회원ID로 강사 조회 Teachers -> applyTeacher -> members -> memId
	public TeachersDTO getOneTeachers(String id) throws NotExistException{
		Teachers teacher = teachersRepository.findByApplyTeacherMembersMemId(id).orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		return mapper.map(teacher, TeachersDTO.class);
	}
	
	//강사번호로 이름만 반환
	public String getOneteacher(int id) {
		
		Optional<Teachers> teacher = teachersRepository.findById(id);
		
		return teacher.get().getApplyTeacher().getMembers().getName();
	}
	
	
}
