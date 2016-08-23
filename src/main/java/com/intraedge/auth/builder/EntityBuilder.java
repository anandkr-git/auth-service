package com.intraedge.auth.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.intraedge.auth.dto.ClientDetails;
import com.intraedge.auth.dto.Role;
import com.intraedge.auth.dto.User;
import com.intraedge.auth.entity.OauthClientDetailsEntity;
import com.intraedge.auth.entity.RoleEntity;
import com.intraedge.auth.entity.UserEntity;

public class EntityBuilder {
	public static Object build(Object object){
		if(object instanceof User){
			User dto = (User)object;
			dto.setPassword(new StandardPasswordEncoder().encode(dto.getPassword()));
			UserEntity entity = new UserEntity();
			//copy basic properties
			BeanUtils.copyProperties(dto, entity);
			//copy the objects - hibernate doesn't like beanutils.copyproperties for objects and list
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			for(Role role: dto.getRoles()){
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setCreatedBy(role.getCreatedBy());
				roleEntity.setCreatedDate(role.getCreatedDate());
				roleEntity.setDescription(role.getDescription());
				roleEntity.setId(role.getId());
				roleEntity.setName(role.getName());
				roleEntity.setUpdatedBy(role.getUpdatedBy());
				roleEntity.setUpdatedDate(role.getUpdatedDate());
				roles.add(roleEntity);
			}
			entity.setRoles(roles);
			return entity;
		}
		if(object instanceof Role){
			Role dto = (Role)object;
			RoleEntity entity = new RoleEntity();
			BeanUtils.copyProperties(dto, entity);
			return entity;
		}
		if(object instanceof ClientDetails){
			ClientDetails dto = (ClientDetails)object;
			OauthClientDetailsEntity entity = new OauthClientDetailsEntity();
			BeanUtils.copyProperties(dto, entity);
			return entity;
		}
		return null;
	}
	
	/*private static AuditEntity buildAuditEntity(AuditInfo auditInfo){
		AuditEntity auditEntity = new AuditEntity();
		if(auditInfo != null){
			auditEntity.setCreatedBy(auditInfo.getCreatedBy());
			auditEntity.setUpdatedBy(auditInfo.getUpdatedBy());
			if(auditInfo.getCreatedDate() == null){
				auditEntity.setCreatedDate(new Date());
			} else{
				auditEntity.setCreatedDate(auditInfo.getCreatedDate());
				auditEntity.setUpdatedDate(new Date());
			}
		} else {
			auditEntity.setCreatedBy("RATING_SERVICES");
			auditEntity.setCreatedDate(new Date());
			auditEntity.setUpdatedBy("RATING_SERVICES");
		}
		return auditEntity;
	}*/
}
