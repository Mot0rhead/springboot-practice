package com.bolsaideas.springboot.form.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.models.domain.Role;


public interface RolService {

	public List<Role>listar();
	public Role obtenerporId(Integer id);
}
