package com.EventosSena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventosSena.entities.Aprendiz;



@Repository
public interface RepositoryAprendiz extends JpaRepository<Aprendiz, Long > {

}
