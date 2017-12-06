package com.schms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schms.domain.Professor;
import com.schms.service.ProfessorService;

@RestController
@RequestMapping(value = "/rest")
public class ProfessorRest {
	

	
	@Autowired
	ProfessorService professorService;
	
	
	@RequestMapping(value = "/professor/{id}",method = RequestMethod.GET)
	public @ResponseBody Professor getProfessor(@PathVariable("id") Long facultyId){
		return professorService.findById(facultyId);
	}
	
}
