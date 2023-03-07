package com.EventosSena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventosSena.entities.Responsible;



@Repository
public interface RepositoryResponsible extends JpaRepository<Responsible, Long >  {

}
