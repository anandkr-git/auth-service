package com.intraedge.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.intraedge.auth.builder.DtoBuilder;
import com.intraedge.auth.builder.EntityBuilder;
import com.intraedge.auth.exception.EntityAlreadyExistsException;
import com.intraedge.auth.exception.EntityNotFoundException;
import com.intraedge.auth.repository.GenericRepository;
import com.intraedge.auth.service.GenericService;
@Service
public abstract class GenericServiceImpl<DTO, Entity> implements GenericService<DTO> {

	protected abstract GenericRepository<Entity> getRepository();
	
	@Override
	@Transactional(readOnly=true)
	public List<DTO> findAll() {
		List<Entity> entities = getRepository().findAll();
		List<DTO> dtoes = new ArrayList<DTO>();
		for(Entity entity: entities){
			dtoes.add(getDTOFromEntity(entity));
		}
		return dtoes;
	}

	@Override
	@Transactional(readOnly=true)
	public DTO findById(Long id) {
		return getDTOFromEntity(getRepository().findOne(id));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public DTO saveOrUpdate(DTO dto) {
		try {
			return getDTOFromEntity(getRepository().save(getEntityFromDTO(dto)));
		}
		catch(DataIntegrityViolationException e){
			throw new EntityAlreadyExistsException("The entity already exists", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		try{
			getRepository().delete(id);
		}
		catch(EmptyResultDataAccessException e){
			throw new EntityNotFoundException("Could not delete, because entity does not exist or was already deleted ",  e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(DTO dto) {
		try{
			getRepository().delete(getEntityFromDTO(dto));
		}
		catch(EmptyResultDataAccessException e){
			throw new EntityNotFoundException("Could not delete, because entity does not exist or was already deleted ",  e);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAll() {
		getRepository().deleteAll();		
	}
	
	@SuppressWarnings("unchecked")
	private DTO getDTOFromEntity(Entity entity){
		return (DTO)DtoBuilder.build(entity);
	}
	
	@SuppressWarnings("unchecked")
	private Entity getEntityFromDTO(DTO dto){
		return (Entity)EntityBuilder.build(dto);
	}

}
