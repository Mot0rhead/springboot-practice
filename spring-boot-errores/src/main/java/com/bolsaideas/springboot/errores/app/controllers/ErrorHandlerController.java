package com.bolsaideas.springboot.errores.app.controllers;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bolsaideas.springboot.errores.app.errores2.UsuarioNoEncontradoException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(ArithmeticException.class)
	public String aritmeticaError( Exception ex, Model model) {
		model.addAttribute("error", "Error aritmetico co co cocoo");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		//return "error/aritmetica";
		return "error/global";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatException( Exception ex, Model model) {
		model.addAttribute("error", "Error de number format execption on on nooo");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		//return "error/numberFormat";
		return "error/global";
	}
	@ExceptionHandler(UsuarioNoEncontradoException.class)
	public String UsuarioNoEncontradoException(UsuarioNoEncontradoException ex, Model model) {
		model.addAttribute("error", "Error usuario no encontrado");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		//return "error/numberFormat";
		return "error/global";
	}
}

