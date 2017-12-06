package com.schms.service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schms.dao.CourseDao;
import com.schms.domain.Course;
import com.schms.service.CourseService;


@Service
public class CourseServiceImpl implements CourseService {
	
	
	@Autowired
	CourseDao courseDao;

	@Override
	public Course findByCourseName(String name) {
		return null;
	}

	@Override
	public Course findById(Long id) {
		return courseDao.findOne(id);
	}

	@Override
	public void save(Course course) {	
		courseDao.save(course);
	}

	@Override
	public void delete(Long id) {
		courseDao.delete(id);
		
	}

	@Override
	public List<Course> getCourses() {
		return (List<Course>)courseDao.findAll();
	}

	@Override
	public List<Course> getProfessorCourses(Long userId) {
		return (List<Course>)courseDao.findByProfessorId(userId);
	}

}
