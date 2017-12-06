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
import org.springframework.web.multipart.MultipartFile;

import com.schms.domain.Assignment;
import com.schms.domain.Course;
import com.schms.domain.User;
import com.schms.service.AssignmentService;
import com.schms.service.CourseService;
import com.schms.service.UserService;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
	
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private CourseService courseService;
	
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/list")
	public String assignments(Model model, HttpServletRequest request){
		
		if(request.isUserInRole("ROLE_ADMIN")){
			List<Assignment> assignments = assignmentService.getAssignments();
			model.addAttribute("assignments",assignments);
		}else{
			User user = getAuthenticatedUser();
			List<Assignment> assignmentsProfessor = assignmentService.getAssignmentsProfessor(user.getId());
			model.addAttribute("assignments",assignmentsProfessor);
		}
		
		return "assignments";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request){
		
		Assignment Assignment = new Assignment();
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses",courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		
		model.addAttribute("assignment", Assignment);
		model.addAttribute("action","register");
		
		return "add_edit_assignment";
	}

	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register( @ModelAttribute("Assignment") Assignment assignment, Model model){
		assignmentService.save(assignment);
		return "redirect:/assignment/list";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Long id, HttpServletRequest request){
		Assignment assignment = assignmentService.findById(id);
		
		if(request.isUserInRole("ROLE_ADMIN")){
			model.addAttribute("courses",courseService.getCourses());
		}else{
			User u = getAuthenticatedUser();
			List<Course> courseProfessorList = courseService.getProfessorCourses(u.getId());
			model.addAttribute("courses", courseProfessorList);
		}
		model.addAttribute("assignment",assignment);
		model.addAttribute("action","edit");
		return "add_edit_assignment";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("assignment") Assignment assignment,Model model){
		assignmentService.save(assignment);
		return "redirect:/assignment/list";
	}
	
	@RequestMapping(value="/remove")
	public String remove(Long id){
		assignmentService.delete(id);
		return "redirect:/assignment/list";
	}
	
	public User getAuthenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User u = userService.findByUsername(authentication.getName());
		return u;
	}
}
