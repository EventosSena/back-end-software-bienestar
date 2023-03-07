package com.EventosSena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventosSena.entities.Event;





@Repository
public interface RepositoryEvent  extends JpaRepository<Event, Long > {

}
