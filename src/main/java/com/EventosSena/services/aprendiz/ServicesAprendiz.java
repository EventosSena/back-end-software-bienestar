package com.EventosSena.services.aprendiz;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EventosSena.entities.Aprendiz;

public interface ServicesAprendiz {
	
	public Iterable<Aprendiz> findAll();
	
	public Page<Aprendiz>findAll(Pageable pageable);
	
	public Optional<Aprendiz>findById(Long  id);
	
	public Aprendiz save(Aprendiz aprendiz);
	
	public void deletById(Long  id);


}
