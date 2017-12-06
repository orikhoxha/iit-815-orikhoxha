package com.schms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schms.domain.Event;

public interface EventDao extends CrudRepository<Event,Long> {
	
	@Query("select evt from Event evt WHERE evt.course.professor.id = :id")
	List<Event> findByProfessorId(@Param("id") Long professorId);

}
