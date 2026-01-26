package com.bolsaideas.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	
	@GetMapping({"/","index"})
	public String index(Model model) {
		model.addAttribute("titulo","Bienvenido al horario de atencion a clientes");
		return "index";
	}
	
	@GetMapping({"/cerrado"})
	public String cerrado(Model model) {
		StringBuilder mensaje = new StringBuilder("El horario es el que nos da la gana ");
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("titulo","Estamos cerrados ");
		return "cerrado";
	}
}
