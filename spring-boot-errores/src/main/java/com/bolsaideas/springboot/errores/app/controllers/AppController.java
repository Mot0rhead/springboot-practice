package com.bolsaideas.springboot.errores.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsaideas.springboot.errores.app.errores2.UsuarioNoEncontradoException;
import com.bolsaideas.springboot.errores.app.models.Usuario;
import com.bolsaideas.springboot.errores.app.services.UsuarioService;

@Controller
public class AppController {

	 @Autowired
	 private UsuarioService usuarioService;
	  
	
	@GetMapping("index")
	public String index() {
	Integer valor = 100/0;
	//	Integer valor = Integer.parseInt("aaa10z");
		return "index";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Integer id,Model model) {
		
		Usuario usuario = usuarioService.obtenerPorIdOptional(id).orElseThrow(()-> new UsuarioNoEncontradoException(id.toString()));
		/*Esto es lo mismo que lo de arriba, pero lo de arriba es mas PRO utilizando el Optional del java 8
		 * if(usuario == null) {
			
			throw new UsuarioNoEncontradoException(id.toString());
		}
		*/
		
		model.addAttribute("titulo", "Detalle del usuario: ".concat(usuario.getNombre()));
		model.addAttribute("usuario", usuario);
		
	//	Integer valor = Integer.parseInt("aaa10z");
		return "ver";
	}
}
