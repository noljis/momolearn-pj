package com.momolearn.model.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
import com.momolearn.model.entity.Teachers;

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
	public List<Members> getApplyMembers() {
//		List<Members> mem = membersRepository
		
		return null;
	}

	// O 강사 신청서 작성
	public ApplyTeacherDTO write(ApplyTeacherDTO applyDTO) throws MessageException{
		System.out.println("ApplyTecacherService.write() : 강사 신청서 작성");
		
		System.out.println("+++1 +++ : " + applyDTO);
		
		ApplyTeacher applyTeachers = applyDTO.toEntity(applyDTO);
		
		System.out.println("+++2 +++ : " + applyTeachers);
		
		System.out.println(applyTeachers);
		try {
			ApplyTeacher applyTeacher = applyTeacherRepository.save(applyTeachers);

			System.out.println(applyTeachers);
			return mapper.map(applyTeacher, ApplyTeacherDTO.class);
			
		} catch (Exception e) {
			throw new MessageException("신청서 등록에 실패했습니다.");
		}
	}	
		
		//		Members members = membersRepository.findById(applyteacher.getMembersMemId()).orElseThrow(()->new NotExistException("존재하지 않는 회원"));
//		ApplyTeacher applyTeacher = applyTeacherRepository.findAll().get(0); //임의 작성 유효한코드X
//				//ApplyTeacherRepository.save(applyteacher.getMembersMemId());
//				//.toEntity(members));
//		System.out.println(applyTeacher.toString());
//		return applyTeacher.getId();
//	}
	
	// O 강사 신청서 상세 보기 : 선택된 memId의 강사 신청서 보기
	public ApplyTeacherDTO read(String membersMemId) throws NotExistException {
		System.out.println("===== service read 1 =====");

		Optional<ApplyTeacher> applyteacher = applyTeacherRepository.findByMembersMemId(membersMemId);
		ApplyTeacher applyTeacher = applyteacher.orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));

		System.out.println("===== service read 2 =====");
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	// X 수정
	@Transactional
	public void update(int id, ApplyTeacherDTO applyDTO) throws NotExistException {
		System.out.println("===== service update apply 11111===== ");
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id).orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacher.setPhoneNum(applyDTO.getPhoneNum());
		applyTeacher.setHopeField(applyDTO.getHopeField());
		applyTeacher.setPfLink(applyDTO.getPfLink());
		applyTeacher.setIntro(applyDTO.getIntro());
		applyTeacherRepository.save(applyTeacher);
		System.out.println("===== service update apply 22222 ===== ");
		System.out.println(applyTeacherRepository.save(applyTeacher));

	}
	
	// O 삭제
	@Transactional
	public void delete(int id) throws NotExistException {
		System.out.println("===== service delete apply ===== ");
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id).orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacherRepository.delete(applyTeacher);
	}
	
	// O 승인
	@Transactional
	public void approve(int id) throws NotExistException {
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(id).orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		
		applyTeacher.setApprove("true");
		applyTeacherRepository.save(applyTeacher);
	    
	}

	// X 강사 신청이 승인된 applyTeacher 정보를 Teacher에 등록
	public Teachers applyToBeTeacher(ApplyTeacher applyTeacher) {
		Teachers newTeacher = mapper.map(applyTeacher, Teachers.class);
		return teachersRepository.save(newTeacher);
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
	
	// id로 강사승인신청 엔티티 불러오기
	public ApplyTeacher getOneApply(String id) throws NotExistException {

		Members member = membersRepository.findById(id).orElseThrow(() -> new NotExistException("해당 회원을 찾을 수 없습니다."));

		return member.getApplyTeacher();
	}

}
