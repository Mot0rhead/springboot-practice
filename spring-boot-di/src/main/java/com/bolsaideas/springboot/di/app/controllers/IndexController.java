package com.bolsaideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsaideas.springboot.di.app.models.service.IServicio;


@Controller
public class IndexController {

	@Autowired
	//@Qualifier("miServicioComplejo")// this annotation find a component named miServicioComplejo and create it
	private IServicio servicio; // using the most abstract  
	
	
	/*public IndexController(IServicio service) {
		this.servicio = service;
	}  Spring inject it automatically*/

	@GetMapping({"/index","","/"})
	public String index(Model model) {
		
		model.addAttribute("objeto", servicio.operacion());
		return "index";
	}

	
	
}
