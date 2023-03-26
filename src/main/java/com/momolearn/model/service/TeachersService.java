package com.momolearn.model.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.dto.TeacherMemberDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Teachers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeachersService {

	private final TeachersRepository teachersRepository;

	private final ApplyTeacherRepository applyTeacherRepository;
	
	private final MembersRepository membersRepository;

	private ModelMapper mapper = new ModelMapper();

	// 강사 신청서 목록 전체 조회
	public List<ApplyTeacherDTO> findAll() {
		List<ApplyTeacher> applylists = applyTeacherRepository.findAll();
		return mapper.map(applylists, new TypeToken<List<ApplyTeacherDTO>>() {
		}.getType());
	}

	//LectureController에서 사용: 회원ID와 승인여부로 강사 조회 Teachers -> applyTeacher -> members -> memId
	public TeacherMemberDTO getOneTeachers(String id) throws NotExistException{
		
		Teachers teacher = teachersRepository.findByMemIdAndApprove(id).orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		
		return new TeacherMemberDTO(teacher.getApplyTeacher().getMembers().getName(), teacher.getTeacherNo());
	}	

//	// id와 승인여부로 강사 한명 조회
//	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException {
//
//		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemIdAndApprove(id)
//				.orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
//
//		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
//
//	}

	// 강사번호로 이름만 반환
	public String getOneteacher(int id) {

		Optional<Teachers> teacher = teachersRepository.findById(id);

		return teacher.get().getApplyTeacher().getMembers().getName();
	}

}
