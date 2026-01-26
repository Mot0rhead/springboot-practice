package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.form.app.services.PaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService service;
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		try {
		Integer id_integer = Integer.parseInt(id);
		this.setValue(service.obtenerPorId(id_integer));
		}catch(NumberFormatException e) {
			this.setValue(null);
		}
	}

	
}
