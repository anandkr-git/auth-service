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

import com.intraedge.auth.dto.ClientDetails;
import com.intraedge.auth.service.ClientDetailsService;

@RestController
@RequestMapping("/api/v1/clientdetails")
public class ClientDetailsController {
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@RequestMapping(
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientDetails>> findAll(){
		return new ResponseEntity<>(clientDetailsService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDetails> findById(@PathVariable("id") String id){
		return new ResponseEntity<>(clientDetailsService.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(
			method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> save(@RequestBody ClientDetails clientDetails, UriComponentsBuilder ucBuilder){
		clientDetails = clientDetailsService.saveOrUpdate(clientDetails);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/clientDetails/{id}").buildAndExpand(clientDetails.getClientId()).toUri());
	    return new ResponseEntity<String>("Resource created successfully", headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			method=RequestMethod.PUT, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> update(@RequestBody ClientDetails clientDetails, UriComponentsBuilder ucBuilder){
		clientDetails = clientDetailsService.saveOrUpdate(clientDetails);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/clientDetails/{id}").buildAndExpand(clientDetails.getClientId()).toUri());
	    return new ResponseEntity<String>("Resource updated successfully", headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.DELETE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable("id") String id, UriComponentsBuilder ucBuilder){
		clientDetailsService.deleteById(id);
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/clientDetails/{id}").buildAndExpand(id).toUri());
	    return new ResponseEntity<String>("Resource deleted successfully", headers, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(
			method=RequestMethod.DELETE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteAll(){
		clientDetailsService.deleteAll();
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>("All Resources deleted successfully", headers, HttpStatus.NO_CONTENT);
	}
}
