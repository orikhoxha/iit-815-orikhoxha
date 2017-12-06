package com.schms.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schms.dao.EventDao;
import com.schms.domain.Event;
import com.schms.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	
	@Autowired
	EventDao eventDao;

	@Override
	public Event findById(Long id) {
		return eventDao.findOne(id);
	}

	@Override
	public Event findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Event event) {
		eventDao.save(event);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		eventDao.delete(id);
	}

	@Override
	public List<Event> getEvents() {
		return (List<Event>)eventDao.findAll();
	}

	@Override
	public List<Event> getEventsProfessor(Long professorId) {
		return (List<Event>)eventDao.findByProfessorId(professorId);
	}
	
	

}
