package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;


public class NombreMayusculaEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		setValue(text.toUpperCase().trim());
	}

}
