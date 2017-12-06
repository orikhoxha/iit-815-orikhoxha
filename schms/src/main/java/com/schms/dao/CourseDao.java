package com.schms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schms.domain.Course;

public interface CourseDao extends CrudRepository<Course,Long> {
	
	@Query("select crs from Course crs WHERE crs.professor.id = :id")
	List<Course> findByProfessorId(@Param("id") Long professorId);
	
}
