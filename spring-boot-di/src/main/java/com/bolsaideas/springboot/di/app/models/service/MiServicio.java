package com.bolsaideas.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Primary
//@Service("miServicioSimple") // u can use @Component but @Service give more semantical sense
public class MiServicio implements IServicio{

	
	@Override // we are overwriting this method
	public String operacion() {
		return "operacion";
	}
}
