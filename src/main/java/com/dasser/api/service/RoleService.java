package com.dasser.api.service;

import java.util.List;

import com.dasser.api.entity.Role;

public interface RoleService {
	
	public abstract List<Role> listAll();
	public abstract List<Role> findRoleByUser(Integer user_id);
	
}
