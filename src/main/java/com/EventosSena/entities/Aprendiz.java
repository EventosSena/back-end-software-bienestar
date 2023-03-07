package com.EventosSena.entities;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "aprendiz")
public class Aprendiz implements Serializable {
	
	public Aprendiz(Long id) {
		super();
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	
	@Column (name = "document")
	private Long document;
	
	@Column (name = "full_name", nullable = false)
	private String full_name;
	
	
	@Column (name = "email", nullable = false)
	private String email;
	
	
	@Column (name = "course", nullable = false)
	private String course;
	

	@JsonIgnoreProperties(value={"aprendiz_id","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY , mappedBy = "aprendiz_id", cascade = CascadeType.ALL)
	private  List<Attendance> attendance;
	
	


	public Aprendiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Aprendiz(Long id, Long document, String full_name, String email, String course,
			List<Attendance> attendance) {
		super();
		this.id = id;
		this.document = document;
		this.full_name = full_name;
		this.email = email;
		this.course = course;
		this.attendance = attendance;
	}






	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Long getDocument() {
		return document;
	}




	public void setDocument(Long document) {
		this.document = document;
	}




	public String getFull_name() {
		return full_name;
	}




	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getCourse() {
		return course;
	}




	public void setCourse(String course) {
		this.course = course;
	}
	
	
	public List<Attendance> getAttendance() {
		return attendance;
	}






	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}


	
	




	

	
	

}
