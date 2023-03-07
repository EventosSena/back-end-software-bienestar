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

import com.EventosSena.entities.Responsible;
import com.EventosSena.services.responsible.ServicesResponsible;




@RestController
@RequestMapping("responsible")
public class ControllerResponsible {
	
	

	@Autowired
	private ServicesResponsible responsibleService;
	
	 /////////////////// RESPONSIBLE REGISTRAR   http://localhost:8080/responsible/save ////////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
		@PostMapping("/save")
		public ResponseEntity<?> create (@RequestBody Responsible responsible){
			
			Responsible newResponsible = null;
			Map<String, Object> response = new HashMap<>();
			
			try {
				
				newResponsible = responsibleService.save(responsible);
			
			} catch (DataAccessException e) {
				
				response.put("Mensaje", "Error al registrar");
				response.put("Error", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("menssaje","El usuario responsible ha sido creado con exito");
			response.put("responsible: ", newResponsible);
			return new ResponseEntity<Map<String, Object >>(response, HttpStatus.CREATED);
			
		}
		
		/////////////////// CONSULT RESPONSIBLE   http://localhost:8080/responsible/consult/ID ////////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
		@GetMapping("/consult/{id}")
		public ResponseEntity<?> consultresponsibleId(@PathVariable(value = "id") Long id){
		
			Optional<Responsible> responsible = null;
			Map<String, Object> response = new HashMap<>();
			
			try {
					responsible=responsibleService.findById(id);
			
			} catch (DataAccessException e) {
				response.put("Mensaje", "Error al hacer consulta en la base de datos");
				response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			if (!responsible.isPresent()) {
				response.put("Mensaje", "EL Responsible con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			return ResponseEntity.ok(responsible);
		}
		
		/////////////////// UPDATE RESPONSIBLE   http://localhost:8080/responsible/update/ID ////////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
		@PutMapping("/update/{id}")
		public ResponseEntity<?>update(@RequestBody Responsible newResponsible,@PathVariable (value = "id")Long id ){
		
			Optional<Responsible> currentResponsible =responsibleService.findById(id) ;
			Optional<Responsible> ResponsibleUpdate = null;
			
			Map<String, Object> response = new HashMap<>();
			
			if(!currentResponsible.isPresent()) {
				response.put("Mensaje", "No se pudo editar el responsible con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			try {
				
				currentResponsible.get().setDocument(newResponsible.getDocument());
				currentResponsible.get().setFull_name(newResponsible.getFull_name());
				currentResponsible.get().setEmail(newResponsible.getEmail());
				currentResponsible.get().setAssociated(newResponsible.getAssociated());
				
		
				
				ResponsibleUpdate = Optional.ofNullable(responsibleService.save(currentResponsible.get()));
			
			} catch (DataAccessException e) {
			
				response.put("Mensaje", "Error al actualizar el responsible en la base de datos");
				response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("Mensaje", "El responsible ha sido actualizado");
			response.put("Aprendiz: ", ResponsibleUpdate);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
		
		/////////////////// DELETE RESPONSIBLE   http://localhost:8080/responsible/delete/ID ////////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteResponsible(@PathVariable(value = "id")  Long id){
			
			Map<String, Object> response = new HashMap<>();
			
			try {
				
				responsibleService.deletById(id);
				
			} catch (DataAccessException e) {

				response.put("Mensaje", "Error al eliminar el responsible en la base de datos");
				response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("Mensaje","El responsible se ha eliminado con exito! ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		/////////////////// CONSULT ALL RESPONSIBLE   http://localhost:8080/responsible/consultall ///////////////
		@CrossOrigin(origins = {"http://localhost:4200","null"})
		@GetMapping("/consultall")
		public  List<Responsible> consultAllUsers(){
			
			
			List<Responsible> responsible = StreamSupport
					.stream(responsibleService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return  responsible;
		}

}
