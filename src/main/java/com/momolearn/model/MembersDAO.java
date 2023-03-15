package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Members;

@Repository
public interface MembersDAO extends JpaRepository<Members, String>{

}
