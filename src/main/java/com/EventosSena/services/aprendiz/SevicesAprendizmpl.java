package com.EventosSena.services.aprendiz;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.EventosSena.entities.Aprendiz;
import com.EventosSena.repositories.RepositoryAprendiz;



@Service
public class SevicesAprendizmpl implements ServicesAprendiz {
	
	@Autowired(required=true)
	private RepositoryAprendiz aprendiz;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aprendiz> findAll() {
		return aprendiz.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Aprendiz> findAll(Pageable pageable) {
		return aprendiz.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aprendiz> findById(Long id) {
		return aprendiz.findById(id);
	}

	@Override
	public Aprendiz save(Aprendiz aprendizvo) {
		return aprendiz.save(aprendizvo);
	}

	@Override
	public void deletById(Long id) {
		aprendiz.deleteById(id);;
		
	}

}
