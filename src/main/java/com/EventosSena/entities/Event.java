package com.EventosSena.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;



@Entity
@Table(name = "event")
public class Event implements Serializable {
	
	public Event(Long id) {
		super();
		this.id = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "date", nullable = false)
	private Timestamp date;

	@Column(name = "place", nullable = false)
	private String place;

	
	

	@JsonIgnoreProperties(value={"responsible_id","hibernateLazyInitializer","handler"},allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id", referencedColumnName = "id" )
	private Responsible responsible_id	;
	
	@JsonIgnoreProperties(value={"event_id","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY , mappedBy = "event_id", cascade = CascadeType.ALL)
	private  List<Attendance> attendance;
	

	

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Event(Long id, String name, String description, Timestamp date, String place, Responsible responsible_id,
			List<Attendance> attendance) {
		super();
		this.id = id;
		this.name = name;
		this.description = description; 
		this.date = date;
		this.place = place;
		this.responsible_id = responsible_id;
		this.attendance = attendance;
	}





	public List<Attendance> getAttendance() {
		return attendance;
	}





	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}





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



	public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.place = place;
	}



	public Responsible getResponsible_id() {
		return responsible_id;
	}



	public void setResponsible_id(Responsible responsible_id) {
		this.responsible_id = responsible_id;
	}

	
	

}
