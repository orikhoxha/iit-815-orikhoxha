package com.schms.service;

import java.util.List;

import com.schms.domain.Course;

public interface CourseService {
	
	Course findByCourseName(String name);
	
	Course findById(Long id);
	
//	boolean checkCourseExists(Long facultyId, String subjectName);
	
	void save(Course course);
	
	void delete(Long id);

	List<Course> getCourses();
	
	List<Course> getProfessorCourses(Long userId);

}
