package com.schms.service;

import java.util.Set;

import com.schms.domain.User;
import com.schms.domain.security.UserRole;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	User save(User user);
	
	User findByUsername(String username);

}
