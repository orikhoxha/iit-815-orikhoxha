package com.schms.controller;

import java.util.List;

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
import com.schms.domain.Schedule;
import com.schms.domain.User;
import com.schms.service.CourseService;
import com.schms.service.ScheduleService;
import com.schms.service.UserService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")
	public String Schedules(Model model, HttpServletRequest request){
		
		
		if(request.isUserInRole("ROLE_ADMIN")){
			List<Schedule> schedules = scheduleService.getSchedules();
			model.addAttribute("schedules",schedules);
		}else{
			User u = getAuthenticatedUser();
			List<Schedule> schedulesProfessor = scheduleService.getSchedulesProfessor(u.getId());
			model.addAttribute("schedules",schedulesProfessor);
		}
	
		return "schedules";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request){
		
		Schedule schedule = new Schedule();
		
		
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses", courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		model.addAttribute("schedule", schedule);
		model.addAttribute("action","register");
		return "add_edit_schedule";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("schedule") Schedule schedule, Model model){
			scheduleService.save(schedule);
			return "redirect:/schedule/list";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Long id, HttpServletRequest request){
		
		Schedule schedule = scheduleService.findById(id);
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses", courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		model.addAttribute("schedule",schedule);
		model.addAttribute("action","edit");
		return "add_edit_schedule";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("schedule") Schedule schedule,Model model){
		scheduleService.save(schedule);
		return "redirect:/schedule/list";
	}
	
	@RequestMapping(value="/remove")
	public String remove(Long id){
		scheduleService.delete(id);
		return "redirect:/schedule/list";
	}
	
	public User getAuthenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User u = userService.findByUsername(authentication.getName());
		return u;
	}

}
