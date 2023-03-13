package com.EventosSena.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventosSena.entities.Attendance;

import com.EventosSena.services.aprendiz.ServicesAprendiz;
import com.EventosSena.services.attendance.ServicesAttendance;
import com.EventosSena.services.event.ServicesEvent;





@RestController
@RequestMapping("attendance")
public class ControllerAttendance {
	
	@Autowired
	private ServicesAttendance servicesAttendance;
	
	@Autowired
	private ServicesEvent servicesEvent;

	@Autowired
	private ServicesAprendiz servicesAprendiz;
	
	 /////////////////// APRENDIZ REGISTRAR   http://localhost:8080/attendance/save ////////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
		@PostMapping("/save")
		public ResponseEntity<?> create (@RequestBody Attendance attendances){
			
			Attendance newAttendance = null;
			Map<String, Object> response = new HashMap<>();
			 
			try {
				
				newAttendance = servicesAttendance.save(attendances);
				System.out.println("id del aprendiz : "+newAttendance.getAprendiz_id()); 
				
			} catch (DataAccessException e) {
				
				response.put("Mensaje", "Error al registrar");
				response.put("Error", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("menssaje","La asistencia ha sido registrada con exito");
			response.put("Aprendiz: ", newAttendance);
			return new ResponseEntity<Map<String, Object >>(response, HttpStatus.CREATED);
			
		
		}
		 /////////////////// APRENDIZ REGISTRAR   http://localhost:8080/attendance/consulta/id ////////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
	    @GetMapping("/consulta/{id}")
	    public ResponseEntity<?> buscarAsistenciaPorCodigo(@PathVariable Long id) {
	        Optional<Attendance> asistenciaEncontrada =servicesAttendance.findById(id);

	        if(!asistenciaEncontrada.isPresent()) {
	            return new ResponseEntity<>("No se encontró asistencia con ese código:" + id, HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(asistenciaEncontrada.get(), HttpStatus.OK);
	    }


}
		


