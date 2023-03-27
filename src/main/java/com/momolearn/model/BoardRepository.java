package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	//조회수 +1
	@Modifying
	@Query("update Board b set b.comViewCount = b.comViewCount+1 where b.comNo=:comNo")
	int increaseCountByComNo(@Param("comNo") int comNo);
}
