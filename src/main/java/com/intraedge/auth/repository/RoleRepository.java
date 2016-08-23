package com.intraedge.auth.repository;

import org.springframework.stereotype.Repository;

import com.intraedge.auth.entity.RoleEntity;

@Repository
public interface RoleRepository extends GenericRepository<RoleEntity> {
	public RoleEntity findByName(String name);
}
