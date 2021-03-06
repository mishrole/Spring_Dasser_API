package com.dasser.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasser.api.entity.Role;
import com.dasser.api.service.RoleService;

@RestController
@RequestMapping(value = "/api/v1/roles")
@CrossOrigin(origins = {"http://localhost:9191","http://localhost:9292"},
methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<Role>> list() {
		List<Role> list = new ArrayList<>();
		
		try {
			list = roleService.listAll();
		} catch (Exception e) {
			return new ResponseEntity<List<Role>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(list);
	}
	
}
