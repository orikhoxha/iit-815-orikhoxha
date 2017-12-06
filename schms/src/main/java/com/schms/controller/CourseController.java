package com.schms.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.schms.domain.Course;
import com.schms.domain.Professor;
import com.schms.domain.Schedule;
import com.schms.domain.User;
import com.schms.domain.security.UserRole;
import com.schms.service.CourseService;
import com.schms.service.ProfessorService;
import com.schms.service.UserService;


@Controller
@RequestMapping("/course")
public class CourseController {
	
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/list",  method = RequestMethod.GET)
	public String courses(Model model, HttpServletRequest request){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println("The authentication" + authentication.toString());
		
		if(request.isUserInRole("ROLE_ADMIN")){
			List<Course> courses = courseService.getCourses();
			model.addAttribute("courses",courses);
			model.addAttribute("enableProfessorName", true);
		}else{
			User user = userService.findByUsername(authentication.getName());
			List<Course> courses = courseService.getProfessorCourses(user.getId());
			model.addAttribute("enableProfessorName", false);
			model.addAttribute("courses",courses);
		}
		return "courses";
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request){
		Course course = new Course();
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("enabled", true);
		}else{
			model.addAttribute("enabled", false);
		}
		
		List<Professor> professors = professorService.getProfessors();		
		System.out.println(professors);
		model.addAttribute("course", course);
		model.addAttribute("action","register");
		model.addAttribute("professors", professors);
		return "add_edit_course";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("course") Course course, Model model,HttpServletRequest request ){
			
		if(!request.isUserInRole("ROLE_ADMIN")){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Professor professor = professorService.findByUsername(authentication.getName());
			course.setProfessor(professor);
		}
			courseService.save(course);		
			return "redirect:/course/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model,  @RequestParam("id") Long id, HttpServletRequest request){
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("enabled", true);
		}else{
			model.addAttribute("enabled", false);
		}
		
		Course course = courseService.findById(id);
		model.addAttribute("professors", professorService.getProfessors());
		model.addAttribute("course",course);
		model.addAttribute("action","edit");
		return "add_edit_course";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("course") Course course,Model model, HttpServletRequest request){
		if(!request.isUserInRole("ROLE_ADMIN")){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Professor professor = professorService.findByUsername(authentication.getName());
			course.setProfessor(professor);
		}
		
		courseService.save(course);
		return "redirect:/course/list";
	}
	
	@RequestMapping(value="/remove")
	public String remove(Long id){
		courseService.delete(id);
		return "redirect:/course/list";
	}
	

}
