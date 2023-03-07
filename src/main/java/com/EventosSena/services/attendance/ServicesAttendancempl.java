package com.EventosSena.services.attendance;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.EventosSena.entities.Attendance;
import com.EventosSena.repositories.RepositoryAttendance;



@Service
public class ServicesAttendancempl implements ServicesAttendance {
	
	

	@Autowired(required=true)
	private RepositoryAttendance attendance;

	@Override
	public Iterable<Attendance> findAll() {
		return attendance.findAll();
	}

	@Override
	public Page<Attendance> findAll(Pageable pageable) {
		return attendance.findAll(pageable);
	}

	@Override
	public Optional<Attendance> findById(Long id) {
		return attendance.findById(id);
	}

	@Override
	public Attendance save(Attendance attendancenvo) {
		return attendance.save(attendancenvo);
	}

	@Override
	public void deletById(Long id) {
		attendance.deleteById(id);
		
	}

}
