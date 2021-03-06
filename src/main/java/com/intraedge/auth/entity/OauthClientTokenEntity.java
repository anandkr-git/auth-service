package com.intraedge.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_token")
public class OauthClientTokenEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//authentication_id VARCHAR(256) PRIMARY KEY,
	@Id
	@Column(name = "authentication_id", nullable = false, unique = true, length = 255)
	private String authenticationId;
	//token_id VARCHAR(256),
	@Column(name = "token_id", length = 256)
	private String tokenId;
  //token LONGVARBINARY,
	@Column(name = "token", length=100000)
	private Byte[] token;
  //user_name VARCHAR(256),
	@Column(name = "user_name", length = 256)
	private String userName;
  //client_id VARCHAR(256)
	@Column(name = "client_id", length = 256)
	private String clientId;
	public String getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
