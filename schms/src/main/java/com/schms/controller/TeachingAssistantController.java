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
import com.schms.domain.TeachingAssistant;
import com.schms.domain.User;
import com.schms.service.CourseService;
import com.schms.service.TeachingAssistantService;
import com.schms.service.UserService;

@Controller
@RequestMapping("/teachingAssistant")
public class TeachingAssistantController {
	
	@Autowired
	private TeachingAssistantService teachingAssistantService;
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/list")
	public String teachingAssistants(Model model, HttpServletRequest request){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(request.isUserInRole("ROLE_ADMIN")){
			List<TeachingAssistant> teachingAssistants = teachingAssistantService.getTeachingAssistants();
			model.addAttribute("teachingAssistants",teachingAssistants);
		}else{
			User user = userService.findByUsername(authentication.getName());
			List<TeachingAssistant> teachingAssistantProfessor = teachingAssistantService.getTeachingAssistantProfessor(user.getId());
			model.addAttribute("teachingAssistants",teachingAssistantProfessor);
		}
		
		return "teachingAssistants";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model,HttpServletRequest request){
		TeachingAssistant teachingAssistant = new TeachingAssistant();
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses", courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		model.addAttribute("teachingAssistant", teachingAssistant);
		model.addAttribute("action","register");
		return "add_edit_teachingAssistant";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("teachingAssistant") TeachingAssistant teachingAssistant, Model model){
			teachingAssistantService.save(teachingAssistant);
			return "redirect:/teachingAssistant/list";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Long id, HttpServletRequest request){
		
		TeachingAssistant teachingAssistant = teachingAssistantService.findById(id);
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses", courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		model.addAttribute("teachingAssistant",teachingAssistant);
		model.addAttribute("action","edit");
		return "add_edit_teachingAssistant";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("teachingAssistant") TeachingAssistant teachingAssistant,Model model){
		teachingAssistantService.save(teachingAssistant);
		return "redirect:/teachingAssistant/list";
	}
	
	@RequestMapping(value="/remove")
	public String remove(Long id){
		teachingAssistantService.delete(id);
		return "redirect:/teachingAssistant/list";
	}
	
	public User getAuthenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User u = userService.findByUsername(authentication.getName());
		return u;
	}

}
