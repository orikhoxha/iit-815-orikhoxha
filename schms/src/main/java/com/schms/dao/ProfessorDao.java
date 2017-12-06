package com.schms.dao;

import org.springframework.data.repository.CrudRepository;

import com.schms.domain.Professor;

public interface ProfessorDao extends CrudRepository<Professor,Long> {
	
	Professor findByName(String name);
	Professor findByEmail(String email);
	Professor findByUsername(String username);

}
