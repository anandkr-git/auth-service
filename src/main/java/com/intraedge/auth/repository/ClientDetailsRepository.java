package com.intraedge.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intraedge.auth.entity.OauthClientDetailsEntity;

@Repository
public interface ClientDetailsRepository extends JpaRepository<OauthClientDetailsEntity, String> {
	
}
