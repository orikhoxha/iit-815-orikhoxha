package com.schms;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.schms.dao.RoleDao;
import com.schms.domain.User;
import com.schms.domain.security.Role;
import com.schms.domain.security.UserRole;
import com.schms.service.UserService;
import com.schms.utility.SecurityUtility;

@SpringBootApplication
public class SchmsApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDao roleDao;

	public static void main(String[] args) {
		SpringApplication.run(SchmsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		User user1 = new User();
		user1.setEnabled(true);
		user1.setUsername("omar");
		
		user1.setPassword(SecurityUtility.passwordEncoder().encode("omar"));
		
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		
		Set<UserRole> userRoles = new HashSet<>();
		
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);
		
		Role role2 = new Role();
		role2.setRoleId(1);
		role2.setName("ROLE_USER");
		roleDao.save(role2);
	}
	
	
}
	