package com.momolearn.model.service;


import java.sql.SQLException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자 자동 생성
public class MembersService {

	@Autowired // 의존관계를 자동으로 설정해 준다. (DAO 객체 주입)
	private MembersRepository membersRepository;

	private ModelMapper mapper = new ModelMapper();

	//회원가입
    @Transactional
    public Members memJoin(Members members) throws SQLException {
    	
        try {
            Members savedMembers = membersRepository.save(members);
            return savedMembers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to join member.");
        }
    }
    
    //id중복체크
    public boolean checkId(String memId) throws Exception {

    	// Members 테이블에서 아이디값들을 가져옴
		List<Members> members  = membersRepository.findAll();
		
		// 컨트롤러에서 전달받은 memId 값과 비교하여 중복 여부를 확인
		for(Members member : members) {
			if(memId.equals(member.getMemId())) {
				return false; // 이미 존재하는 아이디인 경우
			}
		}
		
		return true; // 중복되는 아이디가 없을 경우 true를 리턴
    }
    
	//로그인
    @Transactional
    public Members loginMember(String memId, String password) throws SQLException {
        
    	try {
        	boolean data = validateUser(memId,password);
        	
        	if(data == true) {
        		Members loginData = membersRepository.findByMemIdAndPw(memId, password);
        		System.out.println(loginData);
        		return loginData;
        	}
        	
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("로그인 실패: 아이디를 확인하세요.");
        }
		return null;
    }
	 
    //로그인 회원정보 확인
	public boolean validateUser(String memId, String password) throws Exception {
	
		Members member = membersRepository.findByMemId(memId);
		
		System.out.println("member출력 ===" +member.getPw());
		System.out.println("member출력 ===" +password);
		
        if (member != null && member.getPw().equals(password)) {
            return true;
        }
        return false;
	}
	
	//id찾기 (email로 찾기)
	public Members findId(String email) throws SQLException{
		
		Members member = membersRepository.findByEmail(email);
		
        if (member == null) {
            return null;
        }
        return member;
	}
	
	//pw찾기 (id,email로 찾기)
	public Members findPw(String memId, String email) throws SQLException{
		
		Members member = membersRepository.findByMemIdAndEmail(memId, email);
        if (member == null) {
        	return null;
        }
        return member;
	}
    
	//본인 조회 - jpa
    public Members getMember (String memId) {
    	Members member = membersRepository.findByMemId(memId);
        if (member == null) {
            // 회원 정보를 찾을 수 없는 경우 예외 처리
            throw new RuntimeException("해당 회원을 찾을 수 없습니다.");
        }
        return member;
	}
    
	//id로 한명의 회원정보 불러오기
	public MembersDTO getOneMember(String id) throws NotExistException {

		Members member = membersRepository.findById(id).orElseThrow(() -> new NotExistException("해당 회원을 찾을 수 없습니다."));

		return mapper.map(member, MembersDTO.class);
	}
    
    //본인 프로필 수정 (미확인)
    @Transactional
    public Members updateMember(Members updatedMember) {
        Members member = membersRepository.findById(updatedMember.getMemId())
                                          .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다." + updatedMember.getMemId()));
        
        Members members = null;
		// update the member fields
        member.setPw(updatedMember.getPw());
        member.setName(updatedMember.getName());
        member.setProfile(updatedMember.getProfile());
        

        return membersRepository.save(member);
    }

    
    //회원 한명 삭제  (미확인)
//    @Transactional
//    public void deleteMember(String memId) throws SQLException {
//        try {
//            Optional<Members> membersOptional = membersRepository.findById(memId);
//            if (membersOptional.isPresent()) {
//                Members members = membersOptional.get();
//                membersRepository.delete(members);
//            } else {
//                System.out.println("이미 탈퇴처리가 완료된 회원입니다.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SQLException("Failed to delete member.");
//        }
//    }

	
	//관리자 - 모든 회원 검색
//	public List<Members> getAllMembers() {
//		return membersRepository.findAll();
//	}
	
	//관리자 프로필 수정 -- 회원정보 수정이랑 같이 쓸지?!
//    public boolean adminUpdate() throws SQLException {
//		return false ;
//		
//	}
    

}
