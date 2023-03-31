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
import com.momolearn.model.entity.Comment;
import com.momolearn.model.entity.Likes;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	//조회수 +1
	@Modifying
	@Query("update Board b set b.comViewCount = b.comViewCount+1 where b.comNo=:comNo")
	int increaseCountByComNo(@Param("comNo") int comNo);

	Page<Board> findByComTitleContaining(String searchText, Pageable pageable);
	Page<Board> findByComContentContaining(String searchText, Pageable pageable);
	Page<Board> findByMembers_MemIdContaining(String searchText, Pageable pageable);

//	@Query("select c.com_no, count(c.com_no) from Comment c group by c.com_no order by count(c.com_no) desc limit 3")
//	List<Comment> findTop10ByOrderByCommentsDesc();
//
//	@Query("select l.com_no, count(l.com_no) from Likes l group by l.com_no order by count(l.com_no) desc limit 3")
//	List<Likes> findTop10ByOrderByLikesDesc();

	List<Board> findTop10ByOrderByComViewCountDesc();
}
