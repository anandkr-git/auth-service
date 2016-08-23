package com.intraedge.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.intraedge.auth.dto.Role;
import com.intraedge.auth.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> findAll(){
		return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> findById(@PathVariable("id") long id){
		return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(
			method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> save(@RequestBody Role role, UriComponentsBuilder ucBuilder){
		role = roleService.saveOrUpdate(role);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/roles/{id}").buildAndExpand(role.getId()).toUri());
	    return new ResponseEntity<String>("Resource created successfully", headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			method=RequestMethod.PUT, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> update(@RequestBody Role role, UriComponentsBuilder ucBuilder){
		role = roleService.saveOrUpdate(role);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/roles/{id}").buildAndExpand(role.getId()).toUri());
	    return new ResponseEntity<String>("Resource updated successfully", headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.DELETE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") long id, UriComponentsBuilder ucBuilder){
		roleService.deleteById(id);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/roles/{id}").buildAndExpand(id).toUri());
	    return new ResponseEntity<String>("Resource deleted successfully", headers, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(
			method=RequestMethod.DELETE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteAll(){
		roleService.deleteAll();
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>("All Resources deleted successfully", headers, HttpStatus.NO_CONTENT);
	}
}
