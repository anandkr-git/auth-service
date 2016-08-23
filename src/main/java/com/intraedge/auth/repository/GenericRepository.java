package com.intraedge.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<Entity> extends JpaRepository<Entity, Long> {
	
}
