package com.dasser.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasser.api.entity.SearchUser;
import com.dasser.api.entity.User;
import com.dasser.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> searchUserByNameOrLoginOrStatus(SearchUser bean) {
		return userRepository.searchUserByNameOrLoginOrStatus(bean);
	}
	
	@Override
	public User findUserByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User currentUser = userRepository.findUserByLogin(username);
		
		if(currentUser == null) {
			throw new UsernameNotFoundException("User" + username + " doesn't exist");
		}
		
		List<GrantedAuthority> roles = currentUser.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(currentUser.getLogin(), currentUser.getPassword(), roles);
		return userDetails;
	}

	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveUser(User objUser) {
		return userRepository.save(objUser);
	}
}
