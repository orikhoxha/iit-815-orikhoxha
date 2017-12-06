package com.schms.service;

import java.util.List;

import com.schms.domain.Professor;

public interface ProfessorService {
	
	
	Professor findById(Long id);
	
	Professor findByName(String name);
	
	Professor findByEmail(String email);
	
	Professor findByUsername(String username);
	
	
	void save(Professor professor);
	
	void delete(Long id);
	

	List<Professor> getProfessors();
	
	
	

}
