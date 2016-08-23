package com.intraedge.auth.repository;

import org.springframework.stereotype.Repository;

import com.intraedge.auth.entity.UserEntity;

@Repository
public interface UserRepository extends GenericRepository<UserEntity> {
	public UserEntity findByUsername(String username);
	public UserEntity findByEmail(String email);
}
