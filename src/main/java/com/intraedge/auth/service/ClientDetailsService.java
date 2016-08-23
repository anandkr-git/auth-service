package com.intraedge.auth.service;

import java.util.List;

import com.intraedge.auth.dto.ClientDetails;


public interface ClientDetailsService {
	public List<ClientDetails> findAll();
	public ClientDetails findById(String id);
	public ClientDetails saveOrUpdate(ClientDetails dto);
  public void deleteById(String id);
  public void delete(ClientDetails dto);
  public void deleteAll(); 
}
