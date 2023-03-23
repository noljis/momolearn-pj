package com.momolearn.model;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, String>{
	
//spring data jpa
	
    //모든 회원정보 불러오기
    List<Members> findAll(); 

    //회원가입
    Members save(Members members); 
    
 	//id를 이용해 회원 한명 불러오기
    Members findByMemId(String memId);
    
    //로그인
    Members findByMemIdAndPw(String memId, String pw);
    
    //email로 id찾기
    Members findByEmail(String email);
    
    //id,email로 비빌번호 찾기
    Members findByMemIdAndEmail(String memId, String email);

    // 회원 삭제 
    void deleteByMemId(String memId);
	
    
    
    
}