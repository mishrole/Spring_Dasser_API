package com.dasser.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dasser.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("Select u from User u where u.login = :login")
	public abstract User findUserByLogin(@Param(value = "login") String login);

	@Query("Select u from User u, UserHasRole uhr, Role r, Status s where uhr.user.id = u.id and uhr.role.id = r.id and u.status.id = s.id and (u.lastname is null or u.lastname like :name) and (u.login is null or u.login like :login) and (u.status.id is null or u.status.id = :status)")
	public abstract List<User> searchUser(@Param(value = "login") String login, @Param(value = "name") String name, @Param(value = "status") Integer status);

}
