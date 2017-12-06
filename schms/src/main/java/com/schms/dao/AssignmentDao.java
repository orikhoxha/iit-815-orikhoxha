package com.schms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schms.domain.Assignment;
import com.schms.domain.Course;

public interface AssignmentDao extends CrudRepository<Assignment,Long> {
	
	@Query("select a from Assignment a WHERE a.course.professor.id = :id")
	List<Assignment> findByProfessorId(@Param("id") Long professorId);
	

}
