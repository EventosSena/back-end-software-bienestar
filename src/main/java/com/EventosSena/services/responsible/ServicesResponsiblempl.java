package com.EventosSena.services.responsible;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.EventosSena.entities.Responsible;
import com.EventosSena.repositories.RepositoryResponsible;


@Service
public class ServicesResponsiblempl implements ServicesResponsible {

	@Autowired(required=true)
	private RepositoryResponsible responsible;
	
	@Override
	public Iterable<Responsible> findAll() {
		return responsible.findAll();
	}

	@Override
	public Page<Responsible> findAll(Pageable pageable) {
		return responsible.findAll(pageable);
	}

	@Override
	public Optional<Responsible> findById(Long id) {
		return responsible.findById(id);
	}

	@Override
	public Responsible save(Responsible responsiblevo) {
		return responsible.save(responsiblevo);
	}

	@Override
	public void deletById(Long id) {
		responsible.deleteById(id);
		
	}

}
