package com.UdecPasantiasBack.controller;

import java.util.concurrent.ExecutionException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UdecPasantiasBack.dto.PostDTO;
import com.UdecPasantiasBack.sevice.PostManagementSercice;

@RestController
@RequestMapping(value = "post")
public class PostController {
	
	@Autowired
	private PostManagementSercice service;
	
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
	
	@PostMapping(value = "/add")
	public ResponseEntity add(@RequestBody PostDTO post) throws ExecutionException  {
		return new ResponseEntity(service.add(post),HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}/update")
	public ResponseEntity edit(@PathVariable(value = "id")String id, @RequestBody PostDTO post) {
		return new ResponseEntity(service.edit(id,post),HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity delete(@PathVariable(value = "id") String id) {
		return new ResponseEntity(service.delete(id),HttpStatus.OK);
	}
}