package com.EventosSena.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;



@Entity
@Table(name = "attendace")
public class Attendance implements Serializable {
	
	public Attendance(Long id) {
		super();
		this.id = id;
	}

	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@JsonIgnoreProperties(value={"aprendiz_id","hibernateLazyInitializer","handler"},allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aprendiz_id", referencedColumnName = "id" )
	private Aprendiz aprendiz_id;
	
	@JsonIgnoreProperties(value={"event_id","hibernateLazyInitializer","handler"},allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", referencedColumnName = "id" )
	private Event event_id;

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(Long id, Aprendiz aprendiz_id, Event event_id) {
		super();
		this.id = id;
		this.aprendiz_id = aprendiz_id;
		this.event_id = event_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aprendiz getAprendiz_id() {
		return aprendiz_id;
	}

	public void setAprendiz_id(Aprendiz aprendiz_id) {
		this.aprendiz_id = aprendiz_id;
	}

	public Event getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Event event_id) {
		this.event_id = event_id;
	}
	
	

	
	
	
	

	



}
