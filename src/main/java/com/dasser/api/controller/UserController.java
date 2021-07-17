package com.dasser.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasser.api.entity.SearchUser;
import com.dasser.api.entity.Status;
import com.dasser.api.entity.User;
import com.dasser.api.service.UserService;
import com.dasser.api.util.Constant;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin(origins = {"http://localhost:8090","http://localhost:8091"},
methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<List<User>> list() {
		return ResponseEntity.ok(userService.listAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> find(@PathVariable("id") Integer id) {
		Optional<User> user = userService.findUserById(id);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody User objUser) {
		
		try {
			
			User isUserRegistered = userService.findUserByLogin(objUser.getLogin());
			
			if(isUserRegistered.equals(null)) {
				String passwordBCypt = passwordEncoder.encode(objUser.getPassword());
				objUser.setPassword(passwordBCypt);
				return ResponseEntity.ok(userService.saveUser(objUser));
			} else {
				return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "User already exists.");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been saved.");
		}
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody User objUser) {
		
		try {
			
			String passwordBCypt = passwordEncoder.encode(objUser.getPassword());
			objUser.setPassword(passwordBCypt);
			return ResponseEntity.ok(userService.saveUser(objUser));
	
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been updated.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		
		try {
			
			Optional<User> searchedUser = userService.findUserById(id);
			
			if(searchedUser.isPresent()) {
				User user = searchedUser.get();
				
				Status objStatus = new Status();
				objStatus.setId(3); // Removed
				
				user.setStatus(objStatus);
				User output = userService.saveUser(user);
				
				if(output != null) {					
					return Constant.responseMessage(HttpStatus.OK, "success", "User with id " + id + " has been deleted");
				}
				
				return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "User has not been updated");
				
			}
			
			return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "User with id " + id + " doesn't exist.");
	
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation");
		}
		
	}
	
	@ResponseBody
	@GetMapping(value = "/search")
	public List<User> search(@RequestBody SearchUser bean) {
		return userService.searchUserByNameOrLoginOrStatus(bean);
	}
}
