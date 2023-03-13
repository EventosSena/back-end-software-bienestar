package com.EventosSena.services.attendance;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EventosSena.entities.Attendance;




public interface ServicesAttendance {
	
	
	public Iterable<Attendance> findAll();
	
	public Page<Attendance>findAll(Pageable pageable);
	
	public Optional<Attendance>findById(Long  id);
	
	public Attendance save(Attendance attendance);
	
	public void deletById(Long  id);

}
