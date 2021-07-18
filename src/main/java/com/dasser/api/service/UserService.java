package com.dasser.api.service;

import java.util.List;
import java.util.Optional;

import com.dasser.api.entity.User;

public interface UserService {
	
	public abstract List<User> searchUserByNameOrLoginOrStatus(String login, String name, Integer status);
	public abstract User findUserByLogin(String login);
	public abstract List<User> listAllUsers();
	public abstract Optional<User> findUserById(int id);
	public abstract User saveUser(User objUser);
	public abstract void deleteUser(Integer id);

}
