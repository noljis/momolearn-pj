package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Notice;

@Repository
public interface NoticeDAO extends JpaRepository<Notice, String>{

}
