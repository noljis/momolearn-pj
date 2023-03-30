package com.momolearn.model.service;

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
