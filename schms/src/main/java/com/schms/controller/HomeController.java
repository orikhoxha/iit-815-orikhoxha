package com.schms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.schms.domain.Professor;
import com.schms.service.ProfessorService;


@Controller
public class HomeController {
	
	
	@Autowired
	ProfessorService professorService;

	@RequestMapping("/")
	public String index(){
		return "redirect:/course/list";
//		return "home";
	}
	
	
	@RequestMapping("/login")
	public String login(Model model){
		System.out.println("Getting in here");
		Professor p = new Professor();
		model.addAttribute("professor",p);
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("professor") Professor professor, Model model){
		if(professorService.findByUsername(professor.getUsername()) == null){
			professorService.save(professor);
			model.addAttribute("success", true);
		}else{
			model.addAttribute("exists", true);
		}
		
		return "login";
		
		
	}
}
