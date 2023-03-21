package com.momolearn.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.model.MembersRepository;
import com.momolearn.model.entity.Members;
import com.momolearn.util.DBUtil;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor //생성자 자동 생성
public class MembersService {
	

	@Autowired //의존관계를 자동으로 설정해 준다. (DAO 객체 주입)
	private MembersRepository membersRepository;
	
	@Autowired
	private FileService fileService;

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
        return !membersRepository.existsByMemId(memId);
    }
    
	//로그인
    @Transactional
    public Members loginMember(String memId, String password) throws SQLException {
        try {
        	Members loginData = membersRepository.findByMemIdAndPassword(memId, password);
            System.out.println(loginData);
            return loginData;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to login.");
        }
    }
	 
    //로그인 회원정보 확인
	public boolean validateUser(String memId, String memPassword) throws Exception {
	        return membersRepository.existsByMemIdAndMemPassword(memId, memPassword);
	}
    
	//본인 조회 - jpa
    public static Members getMember (String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Members member = null;
		
		try {
			tx.begin();
			member = em.find(Members.class, id);
			
			System.out.println(member);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			em.close();
		}
		
		return member;
	}
    
    //본인 프로필 수정
    @Transactional
    public Members updateMember(String id, Members updatedMember) {
        Members member = membersRepository.findById(id)
                                          .orElseThrow(() -> new RuntimeException("Member not found with id " + id));
        
        // update the member fields
        member.setMemId(updatedMember.getMemId());
        member.setPw(updatedMember.getPw());
        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        member.setProfile(updatedMember.getProfile());
        

        return membersRepository.save(member);
    }

    
    //회원 한명 삭제
    @Transactional
    public void deleteMember(String id) throws SQLException {
        try {
            Optional<Members> membersOptional = membersRepository.findById(id);
            if (membersOptional.isPresent()) {
                Members members = membersOptional.get();
                membersRepository.delete(members);
            } else {
                System.out.println("이미 탈퇴처리가 완료된 회원입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to delete member.");
        }
    }
	
	

	
	//id찾기 (email로 찾기)
	public Members findId(String email) throws SQLException{
		return membersRepository.findIdByEmail(email);
	}
	
	//pw찾기 (id,email로 찾기)
	public Members findPw(String id, String email) throws SQLException{
		return membersRepository.findPwByIdAndEmail(id, email);
	}
	
	//관리자 - 모든 회원 검색
	public List<Members> getAllMembers() {
		return membersRepository.findAll();
	}
	
	//관리자 프로필 수정 -- 회원정보 수정이랑 같이 쓸지?!
    public boolean adminUpdate() throws SQLException {
		return false ;
		
	}

}
