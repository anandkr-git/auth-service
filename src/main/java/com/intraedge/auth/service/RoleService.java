package com.intraedge.auth.service;

import com.intraedge.auth.dto.Role;

public interface RoleService extends GenericService<Role> {
	public Role findByName(String name);
}
