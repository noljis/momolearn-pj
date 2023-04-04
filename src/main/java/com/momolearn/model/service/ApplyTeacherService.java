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

	// 강사 신청서 전체 목록
	public List<ApplyTeacherDTO> getApplyList() {
		List<ApplyTeacher> applylists = applyTeacherRepository.findAll();
		return Arrays.asList(mapper.map(applylists, ApplyTeacherDTO[].class));
	}
	
	// X 강사 승인 신청서가 있는 회원 목록
	public List<Members> getApplyApproveMembers() {
//		List<Members> applymemlist = membersRepository.findBy;
		return null;
	}

	// O 강사 신청서 작성
	public ApplyTeacherDTO write(ApplyTeacherDTO apply) throws MessageException{
		System.out.println("ApplyTecacherService.write() : 강사 신청서 작성");
		System.out.println("+++ 1 +++ : " + apply); //입력한 정보 받아옴 id, applyRege, approve null값
		ApplyTeacher applyTeachers = apply.toEntity(apply);
		System.out.println("+++ 2 +++ : " + applyTeachers);
		try {
			ApplyTeacher applyTeacher = applyTeacherRepository.save(applyTeachers);
			System.out.println("+++ 3 +++ : " + applyTeachers); //approve = null => default값으로 false
			return mapper.map(applyTeacher, ApplyTeacherDTO.class);
		} catch (Exception e) {
			throw new MessageException("신청서 등록에 실패했습니다.");
		}
	}	
	
	// O 강사 신청서 상세 보기 : 선택된 memId의 강사 신청서 보기
	public ApplyTeacherDTO read(String membersMemId) throws NotExistException {
		System.out.println("+++ service.read 111 +++ ");
		Optional<ApplyTeacher> applyteacher = applyTeacherRepository.findByMembersMemId(membersMemId);
		System.out.println(applyteacher);
		ApplyTeacher applyTeacher = applyteacher.orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));
		System.out.println("+++ service.read 222 +++ ");
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	// O 수정
	@Transactional
	public void update(int id, ApplyTeacherDTO applyDTO) throws NotExistException {
		System.out.println("-------- service.update 111111");
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id).orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacher.setApplyForm(applyDTO.getPhoneNum(), applyDTO.getHopeField(), applyDTO.getPfLink(), applyDTO.getIntro());
		System.out.println(applyDTO.getHopeField() + applyDTO.getIntro() + applyDTO.getPfLink() + applyDTO.getPhoneNum());
		System.out.println("-------- service.update 22222 " + applyTeacher);
		applyTeacherRepository.save(applyTeacher);
	}

	// O 삭제
	@Transactional
	public void delete(int id) throws NotExistException {
		System.out.println("service.delete() : 신청서 삭제 ");
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id).orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacherRepository.delete(applyTeacher);
	}
	
	// O 승인
	@Transactional
	public void approve(int id) throws NotExistException {
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id).orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		System.out.println("service.approve() : 신청서 승인 ");
		applyTeacher.setApprove("true");
		applyTeacherRepository.save(applyTeacher);
		System.out.println("승인 완료 : "+ applyTeacher);
	}

	// 신청서 번호로 1명 조회하기
	public ApplyTeacherDTO getOneApplyTeacher(int id) throws NotExistException {
		System.out.println("service.getOneApplyTeacher() : 신청 번호로 1명 조회" );
		ApplyTeacher applyteacher = applyTeacherRepository.findById(id)
				.orElseThrow(() -> new NotExistException("현재 등록된 신청서가 없습니다."));
		return mapper.map(applyteacher, ApplyTeacherDTO.class);
	}

	// id와 승인여부로 강사 한명 조회
	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException {
		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemIdAndApprove(id)
				.orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	// id로 1개의 강사 신청서 조회
	public ApplyTeacher getOneApply(String id) throws NotExistException {
		Members member = membersRepository.findById(id).orElseThrow(() -> new NotExistException("해당 회원을 찾을 수 없습니다."));
		return member.getApplyTeacher();
	}

}
