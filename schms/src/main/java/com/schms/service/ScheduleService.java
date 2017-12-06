package com.schms.service;

import java.util.List;

import com.schms.domain.Schedule;

public interface ScheduleService {
	
	Schedule findByScheduleName(String name);
		
	Schedule findById(Long id);
	
	boolean checkScheduleExists(Long facultyId, String subjectName);
	
	void save(Schedule schedule);
	
	void delete(Long id);

	List<Schedule> getSchedules();
	
	List<Schedule> getSchedulesProfessor(Long professorId);

}
