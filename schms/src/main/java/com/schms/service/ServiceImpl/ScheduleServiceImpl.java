package com.schms.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schms.dao.ScheduleDao;
import com.schms.domain.Schedule;
import com.schms.service.ScheduleService;


@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	
	@Autowired
	ScheduleDao scheduleDao;

	@Override
	public Schedule findByScheduleName(String name) {
		return null;
	}

	@Override
	public Schedule findById(Long id) {
		return scheduleDao.findOne(id);
	}

	@Override
	public boolean checkScheduleExists(Long facultyId, String subjectName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(Schedule schedule) {
		scheduleDao.save(schedule);
		
	}

	@Override
	public void delete(Long id) {
		scheduleDao.delete(id);
		
	}

	@Override
	public List<Schedule> getSchedules() {
		return (List<Schedule>)scheduleDao.findAll();
	}

	@Override
	public List<Schedule> getSchedulesProfessor(Long professorId) {
		return (List<Schedule>)scheduleDao.findByProfessorId(professorId);
	}

}
