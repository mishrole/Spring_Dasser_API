package com.dasser.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasser.api.entity.SearchUser;
import com.dasser.api.entity.User;
import com.dasser.api.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = {"http://localhost:8090","http://localhost:8091"},
methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@GetMapping(value = "/search")
	public List<User> search(@RequestBody SearchUser bean) {
		return userService.searchUserByNameOrLoginOrStatus(bean);
	}
}
