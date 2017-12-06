package com.schms.service;

import java.util.List;

import com.schms.domain.Assignment;

public interface AssignmentService {
	
	Assignment findById(Long id);
	void save(Assignment assignment);
	void delete(Long id);
	List<Assignment> getAssignments();
	
	List<Assignment> getAssignmentsProfessor(Long professorId);
	
	

}
