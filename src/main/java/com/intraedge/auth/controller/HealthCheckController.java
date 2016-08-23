package com.intraedge.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> healthcheck(){
		return new ResponseEntity<String>("Authentication Service is up and running", HttpStatus.OK);
	}
}
