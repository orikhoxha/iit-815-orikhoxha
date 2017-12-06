package com.schms.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	
	
	//Not shown
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false, updatable = false)
	private Long id;
	
	
	//Shown
	private String courseIdentity;
	
	//Shown
	private String name;
	
	//Shown
	private int term;
	
	
	
	//If professor login add automatically to that professor
	//If admin login choose professor
	//Not shown
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;
	
	
	
	
	//Shown
	//When delete a course, delete a teaching assistant related to that course
	@OneToMany(fetch = FetchType.LAZY, mappedBy="course", cascade=CascadeType.ALL)
	private List<TeachingAssistant> teachingAssistants;
	
	
	//Shown
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Material> materials;
	
	
	//Not shown
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Schedule> schedules;
	
	
	//Shown
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Assignment> assignments;
	
	
	//Not shown
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Event> events;
	
	

	public String getCourseIdentity() {
		return courseIdentity;
	}

	public void setCourseIdentity(String courseIdentity) {
		this.courseIdentity = courseIdentity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	

	public List<TeachingAssistant> getTeachingAssistants() {
		return teachingAssistants;
	}

	public void setTeachingAssistants(List<TeachingAssistant> teachingAssistants) {
		this.teachingAssistants = teachingAssistants;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
