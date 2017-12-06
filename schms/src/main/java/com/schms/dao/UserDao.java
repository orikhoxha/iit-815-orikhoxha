package com.schms.dao;

import org.springframework.data.repository.CrudRepository;

import com.schms.domain.User;

public interface UserDao extends CrudRepository<User,Long> {
	User findByUsername(String username);
}
