package com.intraedge.auth.service;

import com.intraedge.auth.dto.User;

public interface UserService extends GenericService<User> {
	public User findByUsername(String username);
	public User findByEmail(String email);
}
