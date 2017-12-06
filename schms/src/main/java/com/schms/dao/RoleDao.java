package com.schms.dao;

import org.springframework.data.repository.CrudRepository;

import com.schms.domain.security.Role;

public interface RoleDao extends CrudRepository<Role,Long> {
	Role findByName(String name);
}
