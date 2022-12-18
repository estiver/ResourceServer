package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.ws.api.ResourceServer.response.UserRest;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	Environment env;

	@GetMapping("/status/check")
	public String status() {
		return "Working on port: " + env.getProperty("local.server.port");
	}
	
	@PreAuthorize("hasAuthority('ROLE_developer') and #id == #jwt.subject and @securityService.hasClienteRH('12345678000100')") 
	//@Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		System.out.println("clientes: "+ jwt.getClaimAsStringList("clientes_rh"));
        return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
    }
	

	@PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserRest("Sergey", "Kargopolov","5f3fb480-f86c-4514-8d23-ca88d66c6253");
    }
	
}
