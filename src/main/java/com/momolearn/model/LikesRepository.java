package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Likes;
import com.momolearn.model.entity.Members;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer>{
	
	Optional<Likes> findByMembersAndBoard(Members member, Board board);
}
