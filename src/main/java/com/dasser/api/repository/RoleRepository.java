package com.dasser.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasser.api.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	
}
