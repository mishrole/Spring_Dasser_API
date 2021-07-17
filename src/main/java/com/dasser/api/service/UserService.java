package com.dasser.api.service;

import java.util.List;

import com.dasser.api.entity.SearchUser;
import com.dasser.api.entity.User;

public interface UserService {
	public abstract List<User> searchUserByNameOrLoginOrStatus(SearchUser bean);
	public abstract User findUserByLogin(String login);
}
