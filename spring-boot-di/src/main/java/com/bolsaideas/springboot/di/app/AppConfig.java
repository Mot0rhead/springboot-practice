package com.bolsaideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsaideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsaideas.springboot.di.app.models.domain.Producto;
import com.bolsaideas.springboot.di.app.models.service.IServicio;
import com.bolsaideas.springboot.di.app.models.service.MiServicio;
import com.bolsaideas.springboot.di.app.models.service.MiServicioComplejo;

@Configuration
public class AppConfig {

	@Bean("miServicioSimple")
	public IServicio registrarMiServicio() {
		return new MiServicio();
	}
	
	@Bean("miServicioComplejo")
	@Primary
	public IServicio registrarMiServicioComplejo() {
		return new MiServicioComplejo();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems(){
		
		Producto producto1 = new Producto("Camara sony",100);
		Producto producto2 = new Producto("Bicicleta sony",200);
		
		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 4);
		
		return Arrays.asList(linea1,linea2);
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){
		
		Producto producto1 = new Producto("Monitor sony",300);
		Producto producto2 = new Producto("Notebook sony",800);
		Producto producto3 = new Producto("Impresora sony",400);
		Producto producto4 = new Producto("Escritorio sony",200);
		
		ItemFactura linea1 = new ItemFactura(producto1, 4);
		ItemFactura linea2 = new ItemFactura(producto2, 4);
		ItemFactura linea3 = new ItemFactura(producto3, 2);
		ItemFactura linea4 = new ItemFactura(producto4, 10);
		
		return Arrays.asList(linea1,linea2,linea3,linea4);
	}
}
