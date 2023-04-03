package com.momolearn.model.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.dto.TeacherMemberDTO;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.Teachers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeachersService {

	private final TeachersRepository teachersRepository;
	private final ApplyTeacherRepository applyTeacherRepository;
	private final MembersRepository membersRepository;
	private ModelMapper mapper = new ModelMapper();
	
	
	// 강사 신청서 승인이 된 회원의 신청서 정보를 강사 테이블에 등록
	public TeachersDTO saveOneTeacher(ApplyTeacherDTO applyDTO) throws NotExistException, MessageException {
		
		System.out.println("+++ 1 +++ : " + applyDTO);
		Teachers teacher = TeachersDTO.toEntity(applyDTO);
		System.out.println("+++ 2 +++ : " + teacher);
		
		try {
			Teachers teachers = teachersRepository.save(teacher);
			System.out.println("+++ 3 +++ : " + teacher);
			return mapper.map(teachers, TeachersDTO.class);
		} catch (Exception e) {
			throw new MessageException("강사 등록에 실패했습니다.");
		}
	}

	public TeacherMemberDTO getOneTeachers(String id) throws NotExistException{
		
		Teachers teacher = teachersRepository.findByMemIdAndApprove(id).orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		
		return new TeacherMemberDTO(teacher.getApplyTeacher().getMembers().getName(), teacher.getTeacherNo());
	}	

	// 강사번호로 이름만 반환
	public String getOneteacher(int id) {

		Optional<Teachers> teacher = teachersRepository.findById(id);

		return teacher.get().getApplyTeacher().getMembers().getName();
	}

	// 강사 전체 목록
	public List<TeachersDTO> getTeacherList() {
		List<Teachers> tealists = teachersRepository.findAll();
		return Arrays.asList(mapper.map(tealists, TeachersDTO[].class));
	}
	
	// 1개의 강사 번호로 강사 정보 조회
	public TeachersDTO getOneTeacher(int id) throws NotExistException {
		Teachers teacher = teachersRepository.findById(id).orElseThrow(() -> new NotExistException("등록된 강사가 아닙니다."));
		return mapper.map(teacher, TeachersDTO.class);
	}
	
	

}
