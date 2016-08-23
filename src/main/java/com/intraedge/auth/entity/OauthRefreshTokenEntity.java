package com.intraedge.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_refresh_token")
public class OauthRefreshTokenEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
  private Long id;
	//token_id VARCHAR(256),
	@Column(name = "token_id", length = 255)
	private String tokenId;
  //token LONGVARBINARY,
	@Column(name = "token", length=100000)
	private Byte[] token;
  //authentication LONGVARBINARY
	@Column(name = "authentication", length=100000)
	private Byte[] authentication;
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public Byte[] getToken() {
		return token;
	}
	public void setToken(Byte[] token) {
		this.token = token;
	}
	public Byte[] getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Byte[] authentication) {
		this.authentication = authentication;
	}
}
