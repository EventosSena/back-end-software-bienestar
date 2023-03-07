package com.EventosSena.services.event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.EventosSena.entities.Event;
import com.EventosSena.repositories.RepositoryEvent;


@Service
public class ServicesEventmpl implements ServicesEvent{
	
	@Autowired(required=true)
	private RepositoryEvent event;

	@Override
	public Iterable<Event> findAll() {
		return event.findAll();
	}

	@Override
	public Page<Event> findAll(Pageable pageable) {
		return event.findAll(pageable);
	}

	@Override
	public Optional<Event> findById(Long id) {
		return event.findById(id);
	}

	@Override
	public Event save(Event eventvo) {
		return event.save(eventvo);
	}

	@Override
	public void deletById(Long id) {
		event.deleteById(id);
		
	}

}
