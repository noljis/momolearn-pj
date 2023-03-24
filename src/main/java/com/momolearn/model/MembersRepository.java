package com.momolearn.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, String>{
	// id로 회원 한명 불러오기
	Members findByMemId(String memId);
    
    //로그인
    Members findByMemIdAndPw(String memId, String pw);
    
    //email로 id찾기
    Members findByEmail(String email);
    
    //id,email로 비빌번호 찾기
    Members findByMemIdAndEmail(String memId, String email);

    // 회원 삭제 
//    void deleteByMemId(String memId);
    
    //모든 회원정보 불러오기
//  List<Members> findAll(); 
	

   
}