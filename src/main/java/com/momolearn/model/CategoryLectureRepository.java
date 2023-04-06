package com.momolearn.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.CategoryLecture;

@Repository
public interface CategoryLectureRepository extends JpaRepository<CategoryLecture, Integer>{

	List<CategoryLecture> findByLectureId(int id);

	List<CategoryLecture> findByCategoryCateId(int title);
}
