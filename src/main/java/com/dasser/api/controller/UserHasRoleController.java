package com.dasser.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasser.api.entity.Role;
import com.dasser.api.entity.UserHasRole;
import com.dasser.api.entity.UserHasRolePK;
import com.dasser.api.service.RoleService;
import com.dasser.api.service.UserHasRoleService;

@Controller
public class UserHasRoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserHasRoleService userHasRoleService;
	
	@RequestMapping("/save")
	@ResponseBody
	public List<Role> save(Integer user_id, Integer role_id) {
		UserHasRolePK userHasRolePK = new UserHasRolePK();
		userHasRolePK.setUser_id(user_id);
		userHasRolePK.setRole_id(role_id);
		
		UserHasRole userHasRole = new UserHasRole();
		userHasRole.setUserHasRolePK(userHasRolePK);
		
		userHasRoleService.save(userHasRole);
		return roleService.findRoleByUser(user_id);
	
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public List<Role> delete(UserHasRole userHasRole) {
		userHasRoleService.delete(userHasRole);
		return roleService.findRoleByUser(userHasRole.getUserHasRolePK().getUser_id());
	}
}
