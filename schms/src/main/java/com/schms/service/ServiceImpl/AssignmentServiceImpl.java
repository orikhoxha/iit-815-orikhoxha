package com.schms.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schms.dao.AssignmentDao;
import com.schms.domain.Assignment;
import com.schms.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	
	@Autowired
	AssignmentDao assignmentDao;

	@Override
	public Assignment findById(Long id) {
		return assignmentDao.findOne(id);
	}

	@Override
	public void save(Assignment assignment) {
		assignmentDao.save(assignment);
	}

	@Override
	public void delete(Long id) {
		assignmentDao.delete(id);
	}

	@Override
	public List<Assignment> getAssignments() {
		return (List<Assignment>)assignmentDao.findAll();
	}

	@Override
	public List<Assignment> getAssignmentsProfessor(Long professorId) {
		return (List<Assignment>)assignmentDao.findByProfessorId(professorId);
	}
	
	
	
	
	
	

}
