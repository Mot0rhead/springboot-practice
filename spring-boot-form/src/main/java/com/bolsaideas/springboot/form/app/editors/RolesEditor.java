package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.form.app.services.RolService;
import com.bolsaideas.springboot.form.app.services.RolServiceImplements;

@Component
public class RolesEditor extends PropertyEditorSupport{

	@Autowired
	private RolService service;
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		
		try {
			Integer id_integer= Integer.parseInt(id);
			setValue(service.obtenerporId(id_integer));
		}catch(NumberFormatException e) {
			setValue(null);
		}
	}

	
}
