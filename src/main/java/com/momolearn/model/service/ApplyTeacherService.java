package com.momolearn.model.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.aspectj.weaver.NewFieldTypeMunger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.ApplyTeacherRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.TeachersRepository;
import com.momolearn.model.dto.ApplyTeacherDTO;
import com.momolearn.model.entity.ApplyTeacher;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyTeacherService {

	private final TeachersRepository teachersRepository;
	
	private final ApplyTeacherRepository applyTeacherRepository;
	
	private final MembersRepository membersRepository;
	
	private ModelMapper mapper = new ModelMapper();

	public List<ApplyTeacherDTO> getApplyList() {
		
		List<ApplyTeacher> applylists = applyTeacherRepository.findAll();
		return Arrays.asList(mapper.map(applylists, ApplyTeacherDTO[].class));
	}
	
	// X 강사 승인 신청서가 있는 회원 목록
	public List<Members> getApplyApproveMembers() {
//		List<Members> applymemlist = membersRepository.findBy;
		return null;
	}

	public ApplyTeacherDTO write(ApplyTeacherDTO apply) throws MessageException{
		
		ApplyTeacher applyTeachers = apply.toEntity(apply);
		
		try {
			ApplyTeacher applyTeacher = applyTeacherRepository.save(applyTeachers);
			
			return mapper.map(applyTeacher, ApplyTeacherDTO.class);
			
		} catch (Exception e) {
			throw new MessageException("신청서 등록에 실패했습니다.");
		}
	}	
	
	public ApplyTeacherDTO read(String membersMemId) throws NotExistException {

		Optional<ApplyTeacher> applyteacher = applyTeacherRepository.findByMembersMemId(membersMemId);
		ApplyTeacher applyTeacher = applyteacher
				.orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));

		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	@Transactional
	public void update(int id, ApplyTeacherDTO applyDTO) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id)
				.orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacher.setApplyForm(applyDTO.getPhoneNum(), applyDTO.getHopeField(), applyDTO.getPfLink(), applyDTO.getIntro());
	}

	@Transactional
	public void delete(int id) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id)
				.orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacherRepository.delete(applyTeacher);
	}
	
	@Transactional
	public void approve(int id) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id)
				.orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacher.setApprove("true");
	}

	public ApplyTeacherDTO getOneApplyTeacher(int id) throws NotExistException {
		
		System.out.println("service.getOneApplyTeacher() : 신청 번호로 1명 조회" );
		ApplyTeacher applyteacher = applyTeacherRepository.findById(id)
				.orElseThrow(() -> new NotExistException("현재 등록된 신청서가 없습니다."));
		
		return mapper.map(applyteacher, ApplyTeacherDTO.class);
	}

	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemIdAndApprove(id)
				.orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	public ApplyTeacher getOneApply(String id) throws NotExistException {
		
		Members member = membersRepository.findById(id)
				.orElseThrow(() -> new NotExistException("해당 회원을 찾을 수 없습니다."));
		return member.getApplyTeacher();
	}

}
