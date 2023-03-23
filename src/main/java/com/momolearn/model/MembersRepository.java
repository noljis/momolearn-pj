package com.momolearn.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, String> {
	Members save(Members members); // db에 저장

	Optional<Members> findByMemId(String memId); // pk를 이용해 회원 한명 불러오기

	List<Members> findAll(); // 모든 회원정보 불러오기

}