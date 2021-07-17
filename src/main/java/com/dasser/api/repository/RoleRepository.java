package com.dasser.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dasser.api.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("Select r from Role r, UserHasRole uhr where r.id = uhr.role.id and uhr.user.id = :id")
	public abstract List<Role> findRoleByUser(@Param("id") Integer id);
	
}
