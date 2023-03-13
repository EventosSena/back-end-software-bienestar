package com.EventosSena.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventosSena.entities.Event;
import com.EventosSena.services.event.ServicesEvent;



@RestController
@RequestMapping("event")
public class ControllerEvent {
	
	@Autowired
	private ServicesEvent eventService;

	 /////////////////// EVENT REGISTRAR  http://localhost:8080/event/save ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@PostMapping("/save")
	public ResponseEntity<?> create (@RequestBody Event event ){
	
		Event newEvent = new Event(); 
		Map<String, Object> response = new HashMap<>();

		
		try {
			newEvent = eventService.save(event);

			
			
			
		} catch (DataAccessException e) {
			
			response.put("Mensaje", "Error al registrar");
			response.put("Error", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("menssaje","El usuario Event ha sido creado con exito");
		response.put("Aprendiz: ", newEvent);
		return new ResponseEntity<Map<String, Object >>(response, HttpStatus.CREATED);
		
	}
	
	/////////////////// CONSULT EVENT   http://localhost:8080/event/consult/ID ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@GetMapping("/consult/{id}")
	public ResponseEntity<?> consultEventId(@PathVariable(value = "id") Long id){
	
		Optional<Event> event = null;
		Map<String, Object> response = new HashMap<>();
		 
		try {
			event =eventService.findById(id);
			
		
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al hacer consulta en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (!event.isPresent()) {
			response.put("Mensaje", "EL event con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(event);
	}
	/////////////////// UPDATE EVENT   http://localhost:8080/event/update/ID ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@PutMapping("/update/{id}")
	public ResponseEntity<?>update(@RequestBody Event newEvent,@PathVariable (value = "id")Long id ){
	
		Optional<Event> currentEvent = eventService.findById(id);
		Optional<Event> EventUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
	
		if(!currentEvent.isPresent()) {
			response.put("Mensaje", "No se pudo editar el event con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			
			currentEvent.get().setDate(newEvent.getDate());
			currentEvent.get().setDescription(newEvent.getDescription());
			currentEvent.get().setName(newEvent.getName());
			currentEvent.get().setPlace(newEvent.getPlace());
			
			EventUpdate = Optional.ofNullable(eventService.save(currentEvent.get()));
		
		} catch (DataAccessException e) {
		
			response.put("Mensaje", "Error al actualizar el event en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El event ha sido actualizado");
		response.put("event: ", EventUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/////////////////// DELETE EVENT   http://localhost:8080/event/delete/ID////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable(value = "id")  Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			eventService.deletById(id);
			
		} catch (DataAccessException e) {

			response.put("Mensaje", "Error al eliminar el event en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El event se ha eliminado con exito! ");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
		
	/////////////////// CONSULT ALL APRENDIZ   http://localhost:8080/event/consultall ///////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@GetMapping("/consultall")
	public  List<Event> consultAllUsers(){
	
	
		List<Event> event = StreamSupport
			.stream(eventService.findAll().spliterator(), false)
			.collect(Collectors.toList());
		
		return  event;
	}

	

	
	
	
	
	
	

}
