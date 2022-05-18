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
import org.springframework.web.bind.annotation.RestController;

import com.UdecPasantiasBack.dto.Admin_ConvenioDTO;
import com.UdecPasantiasBack.dto.Admin_ISU_DTO;
import com.UdecPasantiasBack.dto.EstudiantesDTO;
import com.UdecPasantiasBack.sevice.EstudiantesService;
import com.UdecPasantiasBack.sevice.implement.AdminnistradorService;

@RestController
@RequestMapping(value = "admin")
public class AdminController {
	@Autowired
	private AdminnistradorService service;
	
	@GetMapping(value = "/greet")
	public String greet() {
		return "hola mundo";
	}
	
	@GetMapping(value = "/greet/{name}")
	public String greet(@PathVariable(value = "name") String name) {
		return "hola "+ name;
	}
	
	/*
	 * ADMINISTRADOR DE CONVENIOS
	 * 
	 * */
	@GetMapping(value = "/convenio/list")
	public ResponseEntity list() {
		return new ResponseEntity(service.list(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/convenio/add")
	public ResponseEntity add(@RequestBody Admin_ConvenioDTO post) throws ExecutionException  {
		return new ResponseEntity(service.add(post),HttpStatus.OK);
	}
	
	@PutMapping(value = "/convenio/{id}/update")
	public ResponseEntity edit(@PathVariable(value = "id")String id, @RequestBody Admin_ConvenioDTO post) {
		return new ResponseEntity(service.edit(id,post),HttpStatus.OK);
	}
	@DeleteMapping(value = "/convenio/{id}/delete")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(service.delete(id),HttpStatus.OK);
	}

/*
 * ADMINISTRADOTR DE 
 * 
 * INTERACCION SOCIAL UNIVESITARIA
 * 
 * */
	
	
	@GetMapping(value = "/isu/list")
	public ResponseEntity listISU() {
		return new ResponseEntity(service.listISU(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/isu/add")
	public ResponseEntity addISU(@RequestBody Admin_ISU_DTO post) throws ExecutionException  {
		return new ResponseEntity(service.addISU(post),HttpStatus.OK);
	}
	
	@PutMapping(value = "/isu/{id}/update")
	public ResponseEntity editISU(@PathVariable(value = "id")String id, @RequestBody Admin_ISU_DTO post) {
		return new ResponseEntity(service.editISU(id,post),HttpStatus.OK);
	}
	@DeleteMapping(value = "/isu/{id}/delete")
	public ResponseEntity deleteISU(@PathVariable(value = "id") String id) {
		return new ResponseEntity(service.deleteISU(id),HttpStatus.OK);
	}
}
