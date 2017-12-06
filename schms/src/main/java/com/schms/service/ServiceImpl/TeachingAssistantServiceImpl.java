package com.schms.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schms.dao.TeachingAssistantDao;
import com.schms.domain.TeachingAssistant;
import com.schms.service.TeachingAssistantService;

@Service
public class TeachingAssistantServiceImpl implements TeachingAssistantService {
	
	
	@Autowired
	TeachingAssistantDao teachingAssistantDao;

	@Override
	public TeachingAssistant findById(Long id) {
		return teachingAssistantDao.findOne(id);
	}

	@Override
	public TeachingAssistant findByName(String name) {
		return null;
	}

	@Override
	public TeachingAssistant findByEmail(String email) {
		return null;
	}

	@Override
	public void save(TeachingAssistant teachingAssistant) {
		teachingAssistantDao.save(teachingAssistant);
		
	}

	@Override
	public void delete(Long id) {
		teachingAssistantDao.delete(id);
		
	}

	@Override
	public List<TeachingAssistant> getTeachingAssistants() {
		return (List<TeachingAssistant>)teachingAssistantDao.findAll();
	}

	@Override
	public List<TeachingAssistant> getTeachingAssistantProfessor(Long id) {
		return (List<TeachingAssistant>)teachingAssistantDao.findByProfessorId(id);
	}
	

}
