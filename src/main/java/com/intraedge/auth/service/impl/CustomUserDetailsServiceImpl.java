package com.intraedge.auth.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.intraedge.auth.entity.RoleEntity;
import com.intraedge.auth.entity.UserEntity;
import com.intraedge.auth.repository.UserRepository;
import com.intraedge.auth.service.CustomUserDetailsService;

@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
    if (userEntity == null) {
        throw new UsernameNotFoundException("Invalid user credentials");
    }
    List<GrantedAuthority> authorities = buildUserAuthority(userEntity.getRoles());
    return buildUserForAuthentication(userEntity, authorities);
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(UserEntity userEntity, 
		List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), 
				userEntity.getPassword(), userEntity.isEnabled(), true, true, true, authorities);
  }
    
  private List<GrantedAuthority> buildUserAuthority(List<RoleEntity> roles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		// Build user's authorities
		for (RoleEntity roleEntity : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(grantedAuthorities);
		return Result;
	}
}
