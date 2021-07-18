package com.dasser.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasser.api.entity.Status;
import com.dasser.api.service.StatusService;

@RestController
@RequestMapping(value = "/api/v1/status")
@CrossOrigin(origins = {"http://localhost:9191","http://localhost:9292"},
methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Status>> list() {
		List<Status> list = new ArrayList<>();
		
		try {
			list = statusService.listAll();
		} catch (Exception e) {
			return new ResponseEntity<List<Status>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(list);
	}
}
