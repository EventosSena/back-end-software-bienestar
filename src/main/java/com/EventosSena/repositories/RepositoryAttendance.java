package com.EventosSena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventosSena.entities.Attendance;

@Repository
public interface RepositoryAttendance extends JpaRepository<Attendance, Long > {

}
