package com.intraedge.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	
	private static final String BEARER_AUTHENTICATION = "Bearer ";
	private static final String HEADER_AUTHORIZATION = "authorization";
	private final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String token = request.getHeader(HEADER_AUTHORIZATION);
		log.info("authorization="+token);
    if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
    	log.info("xxxxx "+token.split(" ")[1]);
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[1]);
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
    }
    response.setStatus(HttpServletResponse.SC_OK);
    response.sendRedirect("login?logout");
	}

}
