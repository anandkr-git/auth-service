package com.intraedge.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intraedge.auth.builder.DtoBuilder;
import com.intraedge.auth.dto.User;
import com.intraedge.auth.entity.UserEntity;
import com.intraedge.auth.repository.GenericRepository;
import com.intraedge.auth.repository.UserRepository;
import com.intraedge.auth.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserEntity> implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected GenericRepository<UserEntity> getRepository() {
		return userRepository;
	}

	@Override
	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		UserEntity entity = userRepository.findByUsername(username);
		return (User)DtoBuilder.build(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
		return (User)DtoBuilder.build(userRepository.findByEmail(email));
	}
}
