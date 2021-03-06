package com.UdecPasantiasBack.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UdecPasantiasBack.dto.EstudiantesDTO;
import com.UdecPasantiasBack.sevice.EstudiantesService;
import com.UdecPasantiasBack.sevice.implement.EstudiantesServicesImpl;

@RestController
@RequestMapping(value = "student")
public class EstudianteController {
	@Autowired
	private EstudiantesServicesImpl service;
	
	@GetMapping(value = "/greet")
	public String greet() {
		return "hola mundo";
	}
	
	@GetMapping(value = "/greet/{name}")
	public String greet(@PathVariable(value = "name") String name) {
		return "hola "+ name;
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity list() {
		return new ResponseEntity(service.list(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/estudiante")
	public EstudiantesDTO student(@RequestParam String id) throws InterruptedException, ExecutionException {
		return service.getEstudiante(id);
	}
	
	
	@PostMapping(value = "/add")
	public ResponseEntity add(@RequestBody EstudiantesDTO post) throws ExecutionException  {
		return new ResponseEntity(service.add(post),HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}/update")
	public ResponseEntity edit(@PathVariable(value = "id")String id, @RequestBody EstudiantesDTO post) {
		return new ResponseEntity(service.edit(id,post),HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(service.delete(id),HttpStatus.OK);
	}

}
