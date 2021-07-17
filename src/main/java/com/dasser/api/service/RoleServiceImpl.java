package com.dasser.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasser.api.entity.Role;
import com.dasser.api.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> listAll() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> findRoleByUser(Integer id) {
		return roleRepository.findRoleByUser(id);
	}

}
