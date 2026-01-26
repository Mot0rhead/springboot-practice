package com.bolsaideas.springboot.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value.isEmpty()||value.isBlank() || value == null) {
			
			return false;
		}
		return true;
	}

}
