package com.intraedge.auth.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.intraedge.auth.dto.ClientDetails;
import com.intraedge.auth.dto.Role;
import com.intraedge.auth.dto.User;
import com.intraedge.auth.entity.OauthClientDetailsEntity;
import com.intraedge.auth.entity.RoleEntity;
import com.intraedge.auth.entity.UserEntity;

public class DtoBuilder {
	public static Object build(Object object){
		//Build User dto
		if(object instanceof UserEntity){
			UserEntity entity = (UserEntity)object;
			User dto = new User();
			List<Role> roles = new ArrayList<Role>();
			BeanUtils.copyProperties(entity, dto);
			for(RoleEntity roleEntity: entity.getRoles()){
				Role role = new Role();
				role.setCreatedBy(roleEntity.getCreatedBy());
				role.setCreatedDate(roleEntity.getCreatedDate());
				role.setDescription(roleEntity.getDescription());
				role.setId(roleEntity.getId());
				role.setName(roleEntity.getName());
				role.setUpdatedBy(roleEntity.getUpdatedBy());
				role.setUpdatedDate(roleEntity.getUpdatedDate());
				roles.add(role);
			}
			dto.setRoles(roles);
			//set basic info
			dto.setPassword("");
			return dto;
		}		
		//Build Role dto
		if(object instanceof RoleEntity){
			RoleEntity entity = (RoleEntity)object;
			Role dto = new Role();
			//set basic info
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		//Build ClientDetails dto
		if(object instanceof OauthClientDetailsEntity){
			OauthClientDetailsEntity entity = (OauthClientDetailsEntity)object;
			ClientDetails dto = new ClientDetails();
			//set basic info
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}
}
