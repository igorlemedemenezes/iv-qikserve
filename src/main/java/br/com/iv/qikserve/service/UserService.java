package br.com.iv.qikserve.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.iv.qikserve.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
