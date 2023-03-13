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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EventosSena.entities.Aprendiz;
import com.EventosSena.entities.Event;
import com.EventosSena.services.aprendiz.ServicesAprendiz;
import com.EventosSena.services.event.ServicesEvent;





@RestController
@RequestMapping("aprendiz")
public class ControllerAprendiz {

	
	@Autowired
	private ServicesAprendiz aprendizService;
	
	@Autowired
	private ServicesEvent eventService;
	
	 /////////////////// APRENDIZ REGISTRAR   http://localhost:8080/aprendiz/save ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@PostMapping("/save")
	public ResponseEntity<?> create (@RequestBody Aprendiz aprendiz){
		
		Aprendiz newAprendiz = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			newAprendiz = aprendizService.save(aprendiz);
			System.out.println(newAprendiz);
			
		} catch (DataAccessException e) {
			
			response.put("Mensaje", "Error al registrar");
			response.put("Error", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("menssaje","El usuario aprendiz ha sido creado con exito");
		response.put("Aprendiz: ", newAprendiz);
		return new ResponseEntity<Map<String, Object >>(response, HttpStatus.CREATED);
		
	}
	
	/////////////////// CONSULT APRENDIZ   http://localhost:8080/aprendiz/consult/ID ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@GetMapping("/consult/{id}")
	public ResponseEntity<?> consultAprendizId(@PathVariable(value = "id") Long id){
	
		Optional<Aprendiz> aprendiz = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			aprendiz =aprendizService.findById(id);
		
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al hacer consulta en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (!aprendiz.isPresent()) {
			response.put("Mensaje", "EL Aprendiz con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(aprendiz);
	}
	
	/////////////////// UPDATE APRENDIZ   http://localhost:8080/aprendiz/update/ID ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@PutMapping("/update/{id}")
	public ResponseEntity<?>update(@RequestBody Aprendiz newAprendiz,@PathVariable (value = "id")Long id ){
	
		Optional<Aprendiz> currentAprendiz = aprendizService.findById(id);
		Optional<Aprendiz> AprendizUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(!currentAprendiz.isPresent()) {
			response.put("Mensaje", "No se pudo editar el aprendiz con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			currentAprendiz.get().setDocument(newAprendiz.getDocument());
			currentAprendiz.get().setCourse(newAprendiz.getCourse());
			currentAprendiz.get().setEmail(newAprendiz.getEmail());
			currentAprendiz.get().setFull_name(newAprendiz.getFull_name());
			
			AprendizUpdate = Optional.ofNullable(aprendizService.save(currentAprendiz.get()));
		
		} catch (DataAccessException e) {
		
			response.put("Mensaje", "Error al actualizar el aprendiz en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El aprendiz ha sido actualizado");
		response.put("Aprendiz: ", AprendizUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/////////////////// DELETE APRENDIZ   http://localhost:8080/aprendiz/delete/ID ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAprendiz(@PathVariable(value = "id")  Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			aprendizService.deletById(id);
			
		} catch (DataAccessException e) {

			response.put("Mensaje", "Error al eliminar el aprendiz en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El aprendiz se ha eliminado con exito! ");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/////////////////// CONSULT ALL APRENDIZ   http://localhost:8080/aprendiz/consultall ///////////////	
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@GetMapping("/consultall")
	public  List<Aprendiz> consultAllUsers(){
		
		
		List<Aprendiz> aprendiz = StreamSupport
				.stream(aprendizService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return  aprendiz;
	}
	 
	
	
	
	/////////////////// CONSULT ATTENDANCE   http://localhost:8080/aprendiz/consultAttendance?id=# ////////////////
	@CrossOrigin(origins = {"http://localhost:4200","null"})
	@GetMapping("/consultAttendance")
	public @ResponseBody String getApr(@RequestParam List<Long> id) {
		
		String a=null;
		String b = null ;
		try {
			Optional<Aprendiz> i = aprendizService.findById(id.get(0));
			Optional<Event> e = eventService.findById(id.get(0));
			a = i.get().getFull_name();
			b = e.get().getName();	
		
			
		} catch (Exception e) {
			System.out.println("adasdasdsd");
		}

			return a+" / "+b;
			
		}
	}


	
	

	
	
	

