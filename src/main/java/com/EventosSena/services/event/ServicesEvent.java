package com.EventosSena.services.event;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EventosSena.entities.Event;




public interface ServicesEvent {
	
	public Iterable<Event> findAll();
	
	public Page<Event>findAll(Pageable pageable);
	
	public Optional<Event>findById(Long  id);
	
	public Event save(Event event);
	
	public void deletById(Long  id);

}
