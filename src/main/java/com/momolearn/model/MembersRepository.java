package com.momolearn.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.entity.Members;

@Repository
public interface MembersRepository extends JpaRepository<MembersDTO, String>{
	MembersDTO save(MembersDTO membersdto); //db에 저장
	Optional<MembersDTO> findByMemId(String memId); //pk를 이용해 회원 한명 불러오기
	List<MembersDTO> findAll(); //모든 회원정보 불러오기
	
	
}
