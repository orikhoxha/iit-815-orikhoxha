package com.schms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.schms.domain.Professor;
import com.schms.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping(value="/list")
	public String professors(Model model){
		List<Professor> professors = professorService.getProfessors();
		model.addAttribute("professors",professors);
		return "professors";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		
		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		model.addAttribute("action","register");
		return "add_edit_professor";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("professor") Professor professor, Model model){
			if(professorService.findByUsername(professor.getUsername()) != null){
				model.addAttribute("exists",true);
			}else{
				professorService.save(professor);
			}
			
			return "redirect:/professor/list";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") Long id){
		Professor professor = professorService.findById(id);
		
		model.addAttribute("professor",professor);
		model.addAttribute("action","edit");
		return "add_edit_professor";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("professor") Professor professor,Model model){
		professorService.save(professor);
		return "redirect:/professor/list";
	}
	
	@RequestMapping(value="/remove")
	public String remove(Long id){
		professorService.delete(id);
		return "redirect:/professor/list";
	}
}
