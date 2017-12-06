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
import com.schms.domain.Event;
import com.schms.domain.User;
import com.schms.service.CourseService;
import com.schms.service.EventService;
import com.schms.service.UserService;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CourseService courseService;
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")
	public String events(Model model, HttpServletRequest request){
		
		if(request.isUserInRole("ROLE_ADMIN")){
			List<Event> events = eventService.getEvents();
			model.addAttribute("events",events);
		}else{
			User u = getAuthenticatedUser();
			List<Event> eventsProfessor = eventService.getEventsProfessor(u.getId());
			model.addAttribute("events",eventsProfessor);
		}
	
		return "events";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request){
		
		Event event = new Event();
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses", courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		
		model.addAttribute("event", event);
		
		model.addAttribute("action","register");
		return "add_edit_event";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("event") Event event, Model model){
			eventService.save(event);
			return "redirect:/event/list";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Long id, HttpServletRequest request){
		Event event = eventService.findById(id);
		model.addAttribute("event",event);
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses", courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		model.addAttribute("action","edit");
		return "add_edit_event";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("event") Event event,Model model){
		eventService.save(event);
		return "redirect:/event/list";
	}
	
	@RequestMapping(value="/remove")
	public String remove(Long id){
		eventService.delete(id);
		return "redirect:/event/list";
	}

	public User getAuthenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User u = userService.findByUsername(authentication.getName());
		return u;
	}
	
	
}
