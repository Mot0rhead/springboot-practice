package com.bolsaideas.springboot.di.app.models.service;


import org.springframework.stereotype.Service;

//@Service("miServicioComplejo")
// u can use @Component but @Service give more semantical sense
public class MiServicioComplejo implements IServicio{

	
	@Override // we are overwriting this method
	public String operacion() {
		return "operacion compleja";
	}
}
