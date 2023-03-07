package com.EventosSena.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "responsible")
public class Responsible  implements Serializable {
	
	
	public Responsible(Long id) {
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
	
	@Column (name = "associated", nullable = false)
	private String associated;
	


	@JsonIgnoreProperties(value={"responsible_id","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY , mappedBy = "responsible_id", cascade = CascadeType.ALL)
	private  List<Event> event;
	
	public Responsible() {
		super();
	}

	public Responsible(Long id, Long document, String full_name, String email, String associated, List<Event> event) {
		super();
		this.id = id;
		this.document = document;
		this.full_name = full_name;
		this.email = email;
		this.associated = associated;
		this.event = event;
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



	public String getAssociated() {
		return associated;
	}



	public void setAssociated(String associated) {
		this.associated = associated;
	}



	public List<Event> getEvent() {
		return event;
	}



	public void setEvent(List<Event> event) {
		this.event = event;
	}
	
	

}
