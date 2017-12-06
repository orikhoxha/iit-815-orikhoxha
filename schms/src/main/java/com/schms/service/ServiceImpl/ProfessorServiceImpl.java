package com.schms.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schms.dao.ProfessorDao;
import com.schms.dao.UserDao;
import com.schms.domain.Professor;
import com.schms.domain.User;
import com.schms.domain.security.Role;
import com.schms.domain.security.UserRole;
import com.schms.service.ProfessorService;
import com.schms.utility.SecurityUtility;


@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	
	@Autowired
	private ProfessorDao professorDao;
	

	@Override
	public Professor findById(Long id) {
		return professorDao.findOne(id);
	}

	@Override
	public Professor findByName(String name) {
		return professorDao.findByName(name);
	}

	@Override
	public Professor findByEmail(String email) {
		return professorDao.findByEmail(email);
	}

	@Override
	public void save(Professor professor) {
		professor.setPassword(SecurityUtility.passwordEncoder().encode(professor.getPassword()));
		professorDao.save(professor);
	}

	@Override
	public void delete(Long id) {
		professorDao.delete(id);
	}

	@Override
	public List<Professor> getProfessors() {
		return (List<Professor>)professorDao.findAll();
	}

	@Override
	public Professor findByUsername(String username) {
		Professor professor = professorDao.findByUsername(username);
		return professor != null ? professor : null;
	}

}
