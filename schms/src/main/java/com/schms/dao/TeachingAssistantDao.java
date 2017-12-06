package com.schms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schms.domain.Course;
import com.schms.domain.TeachingAssistant;

public interface TeachingAssistantDao extends CrudRepository<TeachingAssistant,Long>{
	
	@Query("select ta from TeachingAssistant ta WHERE ta.course.professor.id = :id")
	List<TeachingAssistant> findByProfessorId(@Param("id") Long professorId);

}
