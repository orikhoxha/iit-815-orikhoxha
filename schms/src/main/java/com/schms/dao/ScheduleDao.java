package com.schms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schms.domain.Schedule;

public interface ScheduleDao extends CrudRepository<Schedule,Long> {
	
	@Query("select sch from Schedule sch WHERE sch.course.professor.id = :id")
	List<Schedule> findByProfessorId(@Param("id") Long professorId);

}
