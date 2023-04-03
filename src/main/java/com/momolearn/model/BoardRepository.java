package com.momolearn.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	Page<Board> findByComTitleContaining(String searchText, Pageable pageable);
	Page<Board> findByComContentContaining(String searchText, Pageable pageable);
	Page<Board> findByMembers_MemIdContaining(String searchText, Pageable pageable);

	List<Board> findTop5ByOrderByComViewCountDesc();
	
	Page<Board> findByType(String type, Pageable pageable);
}
