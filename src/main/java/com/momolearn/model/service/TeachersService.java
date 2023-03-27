package com.momolearn.model.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.TeacherMemberDTO;
import com.momolearn.model.entity.Teachers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeachersService {

	private final TeachersRepository teachersRepository;

	private final ApplyTeacherRepository applyTeacherRepository;
	
	private final MembersRepository membersRepository;

	private ModelMapper mapper = new ModelMapper();


	//LectureController에서 사용: 회원ID와 승인여부로 강사 조회 Teachers -> applyTeacher -> members -> memId
	public TeacherMemberDTO getOneTeachers(String id) throws NotExistException{
		
		Teachers teacher = teachersRepository.findByMemIdAndApprove(id).orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		
		return new TeacherMemberDTO(teacher.getApplyTeacher().getMembers().getName(), teacher.getTeacherNo());
	}	


	// 강사번호로 이름만 반환
	public String getOneteacher(int id) {

		Optional<Teachers> teacher = teachersRepository.findById(id);

		return teacher.get().getApplyTeacher().getMembers().getName();
	}

}
