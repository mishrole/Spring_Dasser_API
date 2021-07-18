package com.dasser.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasser.api.entity.UserHasRole;
import com.dasser.api.repository.UserHasRoleRepository;
import com.dasser.api.service.UserHasRoleService;

@Service
public class UserHasRoleServiceImpl implements UserHasRoleService{
	
	@Autowired
	private UserHasRoleRepository userHasRoleRepository;
	
	@Override
	public UserHasRole save(UserHasRole objUserHasRole) {
		return userHasRoleRepository.save(objUserHasRole);
	}

	@Override
	public void delete(UserHasRole objUserHasRole) {
		userHasRoleRepository.delete(objUserHasRole);
	}
	
}
