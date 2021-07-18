package com.dasser.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasser.api.entity.UserRequestModel;
import com.dasser.api.entity.Status;
import com.dasser.api.entity.User;
import com.dasser.api.entity.UserHasRole;
import com.dasser.api.entity.UserHasRolePK;
import com.dasser.api.service.UserHasRoleService;
import com.dasser.api.service.UserService;
import com.dasser.api.util.Constant;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin(origins = {"http://localhost:9191","http://localhost:9292"},
methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserHasRoleService userHasRoleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> list() {
		List<User> list = new ArrayList<>();
		
		try {
			list = userService.listAllUsers();
		} catch(Exception e) {
			return new ResponseEntity<List<User>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find(@PathVariable("id") Integer id) {
		Optional<User> user = userService.findUserById(id);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user);
		}
		
		return Constant.responseMessage(HttpStatus.NOT_FOUND, "Error", "User doesn't exist");
	}
	
	
	@ResponseBody
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> search(@RequestParam(value = "login", required = false) String login, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "status", required = true) Integer status) {
		List<User> list = new ArrayList<>();
		
		try {
			list = userService.searchUserByNameOrLoginOrStatus(login, name, status);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(list, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(list);

	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody UserRequestModel requestUser) {
		
		try {
			
			User isUserRegistered = userService.findUserByLogin(requestUser.getLogin());
			
			if(isUserRegistered == null) {
				
				User objUser = new User();
				
				String passwordBCypt = passwordEncoder.encode(requestUser.getPassword());
				
				BeanUtils.copyProperties(requestUser, objUser);
				objUser.setPassword(passwordBCypt);
				objUser.setCreate_date(new Date());
				objUser.setUpdate_date(new Date());
				
				User userSaved = userService.saveUser(objUser);
				
				if(objUser != null) {
					try {
						UserHasRolePK userHasRolePK = new UserHasRolePK();
						userHasRolePK.setUser_id(userSaved.getId());
						userHasRolePK.setRole_id(requestUser.getRole_id());
						
						UserHasRole userHasRole = new UserHasRole();
						userHasRole.setUserHasRolePK(userHasRolePK);
						
						UserHasRole output = userHasRoleService.save(userHasRole);
						
						if(output == null) {
							userService.deleteUser(userSaved.getId());
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				return Constant.responseMessage(HttpStatus.OK, "Success", "User has been created");
				
			} else {
				return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "User already exists");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been saved");
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody UserRequestModel requestUser) {
		
		try {
			
			Optional<User> searchedUser = userService.findUserById(requestUser.getId());
			
			if(searchedUser.isPresent()) {
				
				User objUser = new User();
				BeanUtils.copyProperties(requestUser, objUser);
				
				String passwordBCypt = passwordEncoder.encode(requestUser.getPassword());
				
				if(passwordBCypt != searchedUser.get().getPassword()) {
					objUser.setPassword(passwordBCypt);
				}
				
				objUser.setLogin(searchedUser.get().getLogin());
				objUser.setCreate_date(searchedUser.get().getCreate_date());
				objUser.setUpdate_date(new Date());
				
				User userSaved = userService.saveUser(objUser);
				
				if(userSaved != null) {
					
					try {
						UserHasRolePK userHasRolePK = new UserHasRolePK();
						userHasRolePK.setUser_id(userSaved.getId());
						userHasRolePK.setRole_id(requestUser.getRole_id());
						
						UserHasRole userHasRole = new UserHasRole();
						userHasRole.setUserHasRolePK(userHasRolePK);
						
						UserHasRole output = userHasRoleService.save(userHasRole);
						
						if(output == null) {
							userService.deleteUser(userSaved.getId());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				return Constant.responseMessage(HttpStatus.OK, "Success", "User has been updated");
				
			} else {
				return Constant.responseMessage(HttpStatus.NOT_FOUND, "Error", "User doesn't exist");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.responseMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Error", "An error occurred while performing the operation, the user has not been updated.");
		}
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		
		try {
			
			Optional<User> searchedUser = userService.findUserById(id);
			
			if(searchedUser.isPresent()) {
				User user = searchedUser.get();
				
				if(user.getStatus().getId().equals(3)) {
					return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "The user has already been removed");
				}
				
				Status objStatus = new Status();
				objStatus.setId(3); // Removed
				
				user.setStatus(objStatus);
				User output = userService.saveUser(user);
				
				if(output != null) {					
					return Constant.responseMessage(HttpStatus.OK, "Success", "User with id " + id + " has been removed");
				}
				
				return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "User has not been removed");
				
			}
			
			return Constant.responseMessage(HttpStatus.BAD_REQUEST, "Error", "User with id " + id + " doesn't exist");
	
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.responseMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Error", "An error occurred while performing the operation");
		}

	}

}
