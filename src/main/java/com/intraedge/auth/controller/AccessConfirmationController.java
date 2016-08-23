package com.intraedge.auth.controller;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.intraedge.auth.dto.ClientDetails;
import com.intraedge.auth.service.ClientDetailsService;

@Controller
@SessionAttributes(types = AuthorizationRequest.class)
public class AccessConfirmationController {
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@RequestMapping(method=RequestMethod.GET, value= "/oauth/confirm_access")
  public ModelAndView getAccessConfirmation(@ModelAttribute AuthorizationRequest clientAuth) throws Exception {
		ClientDetails client = clientDetailsService.findById(clientAuth.getClientId());
		System.out.println("client obtained="+client.getClientId());
		System.out.println(clientAuth.getRedirectUri());
		TreeMap<String, Object> model = new TreeMap<String, Object>();
    model.put("auth_request", clientAuth);
    model.put("client", client);
    return new ModelAndView("confirmAccess", model);
	}
	
}
