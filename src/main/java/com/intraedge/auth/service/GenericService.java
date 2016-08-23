package com.intraedge.auth.service;

import java.util.List;

public interface GenericService<DTO> {
	public List<DTO> findAll();
	public DTO findById(Long id);
	public DTO saveOrUpdate(DTO dto);
  public void deleteById(Long id);
  public void delete(DTO dto);
  public void deleteAll();
}
