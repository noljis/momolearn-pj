package com.momolearn.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.model.entity.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, String>{
	
	//spring data jpa
	//회원가입
    @Transactional
    default Members memJoin(Members members) {
        return save(members);
    }
    
    //id중복체크
    boolean existsByMemId(String memId);
    
  //db에 저장
    Members save(Members membersd); 
    
    //사용자 인증
    boolean existsByMemIdAndMemPassword(String memId, String memPassword);
    
 	//pk를 이용해 회원 한명 불러오기
    Members findByMemId(String memId);
    
    //모든 회원정보 불러오기
    List<Members> findAll(); 
    
    // 회원 삭제 
    void deleteByMemId(String memId);
    
    //로그인
    Members findByMemIdAndPassword(String memId, String password);
    
    //email로 id찾기
    Members findIdByEmail(String email);
    
    //id,email로 비빌번호 찾기
    Members findPwByIdAndEmail(String id, String email);
}