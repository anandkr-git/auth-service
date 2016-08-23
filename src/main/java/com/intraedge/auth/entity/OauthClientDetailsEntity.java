package com.intraedge.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetailsEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//client_id VARCHAR(256) PRIMARY KEY,
	@Id
	@Column(name = "client_id", nullable = false, unique = true, length = 255)
	private String clientId;
  //resource_ids VARCHAR(256),
	@Column(name = "resource_ids", length = 256)
	private String resourceIds;
  //client_secret VARCHAR(256),
	@Column(name = "client_secret", length = 256)
	private String clientSecret;
  //scope VARCHAR(256),
	@Column(name = "scope", length = 256)
	private String scope;
  //authorized_grant_types VARCHAR(256),
	@Column(name = "authorized_grant_types", length = 256)
	private String authorizedGrantTypes;
  //web_server_redirect_uri VARCHAR(256),
	@Column(name = "web_server_redirect_uri", length = 256)
	private String webServerRedirectUri;
  //authorities VARCHAR(256),
	@Column(name = "authorities", length = 256)
	private String authorities;
  //access_token_validity INTEGER,
	@Column(name = "access_token_validity")
	private int accessTokenValidity;
  //refresh_token_validity INTEGER,
	@Column(name = "refresh_token_validity")
	private int refreshTokenValidity;
  //additional_information VARCHAR(4096),
	@Column(name = "additional_information", length = 4096)
	private String additionalInformation;
  //autoapprove VARCHAR(256)
	@Column(name = "autoapprove", length = 256)
	private String autoapprove;
	
	//extra columns
	@Column(name = "client_name", length = 256)
	private String clientName;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}
	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}
	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}
	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}
	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getAutoapprove() {
		return autoapprove;
	}
	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
