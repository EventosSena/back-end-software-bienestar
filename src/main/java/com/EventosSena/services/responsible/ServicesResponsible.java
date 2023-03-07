package com.EventosSena.services.responsible;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EventosSena.entities.Responsible;


public interface ServicesResponsible {
	
	
	public Iterable<Responsible> findAll();
	
	public Page<Responsible>findAll(Pageable pageable);
	
	public Optional<Responsible> findById(Long  id);
	
	public Responsible save(Responsible responsible);
	
	public void deletById(Long  id);

}
