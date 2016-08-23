package com.intraedge.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.intraedge.auth.builder.DtoBuilder;
import com.intraedge.auth.builder.EntityBuilder;
import com.intraedge.auth.dto.ClientDetails;
import com.intraedge.auth.entity.OauthClientDetailsEntity;
import com.intraedge.auth.repository.ClientDetailsRepository;
import com.intraedge.auth.service.ClientDetailsService;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Override
	@Transactional(readOnly=true)
	public List<ClientDetails> findAll() {
		List<OauthClientDetailsEntity> entities = clientDetailsRepository.findAll();
		List<ClientDetails> dtoes = new ArrayList<ClientDetails>();
		for(OauthClientDetailsEntity entity: entities){
			dtoes.add(getDTOFromEntity(entity));
		}
		return dtoes;
	}

	@Override
	@Transactional(readOnly=true)
	public ClientDetails findById(String id) {
		return getDTOFromEntity(clientDetailsRepository.findOne(id));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientDetails saveOrUpdate(ClientDetails dto) {
		//Generate a random client id of the form ABCD-EFGH-IJKL-MNOP-QRST
		if(dto.getClientId() == null){
			dto.setClientId(generateClientId());
		}
		return getDTOFromEntity(clientDetailsRepository.save(getEntityFromDTO(dto)));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(String id) {
		clientDetailsRepository.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(ClientDetails dto) {
		clientDetailsRepository.delete(getEntityFromDTO(dto));		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAll() {
		clientDetailsRepository.deleteAll();		
	}
	
	private ClientDetails getDTOFromEntity(OauthClientDetailsEntity entity){
		return (ClientDetails)DtoBuilder.build(entity);
	}
	
	private OauthClientDetailsEntity getEntityFromDTO(ClientDetails dto){
		return (OauthClientDetailsEntity)EntityBuilder.build(dto);
	}
	
	private String generateClientId(){
		String clientIdWithoutDashes = RandomStringUtils.randomAlphabetic(20).toUpperCase();
		StringBuilder clientIdBuilder = new StringBuilder();
		clientIdBuilder
		.append(clientIdWithoutDashes.substring(0, 4))
		.append("-")
		.append(clientIdWithoutDashes.substring(4, 8))
		.append("-")
		.append(clientIdWithoutDashes.substring(8, 12))
		.append("-")
		.append(clientIdWithoutDashes.substring(12, 16))
		.append("-")
		.append(clientIdWithoutDashes.substring(16, 20))
		;	
		return clientIdBuilder.toString();
	}

}
