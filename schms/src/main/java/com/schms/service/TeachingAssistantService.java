package com.schms.service;

import java.util.List;

import com.schms.domain.TeachingAssistant;

public interface TeachingAssistantService {
	
	TeachingAssistant findById(Long id);
	
	TeachingAssistant findByName(String name);
	
	TeachingAssistant findByEmail(String email);
	
	void save(TeachingAssistant professor);
	
	void delete(Long id);

	List<TeachingAssistant> getTeachingAssistants();
	
	List<TeachingAssistant> getTeachingAssistantProfessor(Long id);

}
