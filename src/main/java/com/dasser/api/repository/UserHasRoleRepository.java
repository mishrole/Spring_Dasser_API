package com.dasser.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasser.api.entity.UserHasRole;

public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Integer> {

}
