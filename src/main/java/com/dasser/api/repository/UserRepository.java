package com.dasser.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dasser.api.entity.SearchUser;
import com.dasser.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("Select u from User u where u.login = :login")
	public abstract User findUserByLogin(@Param(value = "login") String login);
	
	@Query("Select u from User u where u.login = :#{#user.login}")
	public abstract List<User> searchUserByNameOrLoginOrStatus(@Param(value = "user") SearchUser bean);
	
}
