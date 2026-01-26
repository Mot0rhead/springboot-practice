package com.bolsaideas.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsaideas.springboot.web.app.models.User;



@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${texto.indexController.index.titulo}")
	private String textoIndex;
	@Value("${texto.indexController.index.perfil}")
	private String textoPerfil;
	@Value("${texto.indexController.index.listar}")
	private String textoListar;
	@GetMapping({"/index","/","/home"})
	public String index(Model model) { /* instead you can use ModelMap , Map o ModelAndView */
		model.addAttribute("titulo",textoIndex);
		return "index";
	}
	@GetMapping("/perfil")
	public String profile(Model model) {
		User user = new User();
		user.setUsername("nombre1");
		user.setLastname("apellido 1");
		user.setEmail("correo@gamama");
		model.addAttribute("usuario",user);
		model.addAttribute("titulo",textoPerfil.concat(user.getUsername()));
		return "perfil";// we return the view for HTML
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", textoListar);
	/*	List<User> usuarios = new ArrayList<>();
		usuarios.add(new User("Andres","Malboro","andre@malboro"));
		usuarios.add(new User("John","Camel","camel@john"));
		usuarios.add(new User("Heidy","Lucky",""));
		model.addAttribute("usuarios",usuarios);*/
		return "listar";
	}
	
	 @ModelAttribute("usuarios") // this "variable" found in all the get points, is like universal. This will use if u are going to use this variable in different methods
	public List<User> poblarUsuarios(Model model) {
		List<User> usuarios = new ArrayList<>();
		usuarios.add(new User("Andres","Malboro","andre@malboro"));
		usuarios.add(new User("John","Camel","camel@john"));
		usuarios.add(new User("Heidy","Lucky",""));
		return usuarios;
	}
}
