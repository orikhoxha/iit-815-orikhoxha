package com.schms.service;

import java.util.List;

import com.schms.domain.Event;


public interface EventService {
	
	Event findById(Long id);
	
	Event findByName(String name);
	
	Event findByEmail(String email);
	
	void save(Event event);
	
	void delete(Long id);

	List<Event> getEvents();
	
	List<Event> getEventsProfessor(Long professorId);

}
