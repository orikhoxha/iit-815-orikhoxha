package com.schms.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Assignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false, updatable = false)
	private Long id;
	
	private String name;
	private String description;
	private String dueDate;
	
	@Transient
	private List<MultipartFile> assignmentResources;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String date) {
		this.dueDate = date;
	}

	public List<MultipartFile> getAssignmentResources() {
		return assignmentResources;
	}

	public void setAssignmentResources(List<MultipartFile> assignmentResources) {
		this.assignmentResources = assignmentResources;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
	

}
