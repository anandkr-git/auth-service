package com.intraedge.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intraedge.auth.builder.DtoBuilder;
import com.intraedge.auth.dto.Role;
import com.intraedge.auth.entity.RoleEntity;
import com.intraedge.auth.repository.GenericRepository;
import com.intraedge.auth.repository.RoleRepository;
import com.intraedge.auth.service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, RoleEntity> implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	protected GenericRepository<RoleEntity> getRepository() {
		return roleRepository;
	}

	@Override
	@Transactional(readOnly=true)
	public Role findByName(String name) {
		return (Role)DtoBuilder.build(roleRepository.findByName(name));
	}

}
