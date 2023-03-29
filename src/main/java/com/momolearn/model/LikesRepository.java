package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Likes;
import com.momolearn.model.entity.Members;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer>{
	
	Optional<Likes> findByMembersAndBoard(Members member, Board board);
	
	@Query(value="select count(*) from likes l where l.com_no=:com_no", nativeQuery = true)
	Long countByComNo(@Param("com_no") int comNo);

	List<Likes> findAllByBoard(Board board);

}
