package com.dasser.api.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.dasser.api.entity.User;
import com.dasser.api.service.UserService;

@Component
public class TokenEnhancerConfig implements TokenEnhancer {
	
	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = userService.findUserByLogin(authentication.getName());
		
		Map<String, Object> information = new HashMap<>();
		information.put("name", user.getLastname() + ' ' + user.getFirstname());
		information.put("email", user.getLogin());
		information.put("role", user.getRoles());
		information.put("access", user.getStatus().getName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
		return accessToken;
		
	}

}
