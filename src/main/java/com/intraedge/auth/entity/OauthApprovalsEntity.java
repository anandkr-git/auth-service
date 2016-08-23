package com.intraedge.auth.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="oauth_approvals")
public class OauthApprovalsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
  private Long id;
	
	//userId VARCHAR(256),
	@Column(name = "userId", nullable = false, length = 255)
	private String userId;
	//clientId VARCHAR(256),
	@Column(name = "clientId", length = 256)
	private String clientId;
	//scope VARCHAR(256),
	@Column(name = "scope", length = 256)
	private String scope;
	//status VARCHAR(10),
	@Column(name = "status", length = 10)
	private String status;
	//expiresAt TIMESTAMP,
	@Column(name = "expiresAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiresAt;
	//lastModifiedAt TIMESTAMP
	@Column(name = "lastModifiedAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedAt;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
}
