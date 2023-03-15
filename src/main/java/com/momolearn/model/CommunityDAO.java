package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Community;

@Repository
public interface CommunityDAO extends JpaRepository<Community, String>{

}
