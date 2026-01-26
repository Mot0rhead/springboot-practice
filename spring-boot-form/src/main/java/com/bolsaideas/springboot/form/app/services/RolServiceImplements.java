package com.bolsaideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.models.domain.Role;

@Service
public class RolServiceImplements implements RolService {

	private List<Role> roles;
	
	public RolServiceImplements() {
	
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1,"Admin","ROLE_ADMIN"));
		this.roles.add(new Role(2,"User","ROLE_USER"));
		this.roles.add(new Role(3,"Moderator","ROLE_MODERATOR"));
		
	}

	@Override
	public List<Role> listar() {
	
		return roles;
	}

	@Override
	public Role obtenerporId(Integer id) {
		Role resultado = null;
		
		for (Role role : roles) {
			if( role.getId() == id) {
				resultado = role;
				break;
			}
		}
			
			return resultado;
	}

}
