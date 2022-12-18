package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {

	public boolean hasClienteRH(String cnpj) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((Jwt)authentication.getCredentials()).getClaimAsStringList("clientes_rh").contains(cnpj);
	}
}
