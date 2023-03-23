package com.momolearn.model.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.dto.TeachersDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.Teachers;

@Service
public class TeachersService {

	@Autowired
	private TeachersRepository teachersRepository;

	@Autowired
	private ApplyTeacherRepository applyTeacherRepository;

	private ModelMapper mapper = new ModelMapper();

	public ApplyTeacher convertDtoToEntity(ApplyTeacherDTO dto) {
		return ApplyTeacher.builder().id(dto.getId()).phoneNum(dto.getPhoneNum()).hopeFiled(dto.getHopeFiled())
				.intro(dto.getIntro()).pfLink(dto.getPfLink()).approve(dto.getApprove())
				.members(new Members(dto.getMembersMemId())).build();

		//members 타입이 mapping이 안되고있음 -> builder패턴으로 하나하나 만들어서 반환
//        return mapper.map(dto, ApplyTeacher.class);
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
	
	// 강사 신청 승인하기 : approve를 false -> true로 변경
	@Modifying
	@Transactional
	public void approve(String id) {
		applyTeacherRepository.approve(id);
	}

	// 강사 신청이 승인된 applyTeacher 정보를 Teacher에 등록
	public Teachers applyToBeTeacher(ApplyTeacher applyTeacher) {
		Teachers newTeacher = mapper.map(applyTeacher, Teachers.class);
		return teachersRepository.save(newTeacher);
	}

	// id와 승인여부로 강사 한명 조회
	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException {

		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemIdAndApprove(id)
				.orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));

		return mapper.map(applyTeacher, ApplyTeacherDTO.class);

	}

}
