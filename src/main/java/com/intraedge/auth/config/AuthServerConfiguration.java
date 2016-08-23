package com.intraedge.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.intraedge.auth.service.CustomUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;
	
	@Autowired
	ClientDetailsService clientDetailsService;
	
	@Autowired
  private DataSource dataSource;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) 
    throws Exception {
      oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }
	
	@Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource);
  }
	
	@Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) 
    throws Exception {
		// @formatter:off
    endpoints
    	.tokenStore(tokenStore())
      .authenticationManager(authenticationManager)
      .userDetailsService(userDetailsService)
      .userApprovalHandler(userApprovalHandler())
      ;
    // @formatter:on
  }

  @Bean
  public TokenStore tokenStore() {
      return new JdbcTokenStore(dataSource);
  }
  
  @Bean
  public UserApprovalHandler userApprovalHandler() throws Exception {
  		ApprovalStoreUserApprovalHandler approvalHandler = new ApprovalStoreUserApprovalHandler();
  		approvalHandler.setApprovalStore(approvalStore());
  		approvalHandler.setClientDetailsService(clientDetailsService);
  		approvalHandler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
      //approvalHandler.setScopePrefix(scopePrefix);
      return approvalHandler;
  }
  
  @Bean
  public ApprovalStore approvalStore() {
  		return new JdbcApprovalStore(dataSource);
  }
	
}
