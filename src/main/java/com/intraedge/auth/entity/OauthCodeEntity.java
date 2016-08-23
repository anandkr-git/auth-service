package com.intraedge.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_code")
public class OauthCodeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
  private Long id;
	//code VARCHAR(256),
	@Column(name = "code", length = 256)
	private String code;
	//authentication LONGVARBINARY
	@Column(name = "authentication", length=100000)
	private Byte[] authentication;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Byte[] getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Byte[] authentication) {
		this.authentication = authentication;
	}
}
