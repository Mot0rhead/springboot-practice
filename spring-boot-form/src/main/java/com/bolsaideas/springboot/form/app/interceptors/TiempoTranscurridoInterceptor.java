package com.bolsaideas.springboot.form.app.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("tiempoTranscurrido")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {
	
	private static final Logger Logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("post")) { // con este vemos si la request es del tipo post y si lo es pues hacemos return para no hacer todas las comprobaciones y el tiempo de espera para la peticion post
			return true;
		}
		
		if(handler instanceof HandlerMethod) { 
			HandlerMethod metodo =(HandlerMethod) handler;
			Logger.info("Es un metodo del controlador"+ metodo.getMethod().getName()); // podemos coger toda la informacion que envuelve al metodo del controlador
		}
		Logger.info("TiempoTranscurridoInterceptor prehandle entrando....");
		Logger.info("Interceptarndo handler...."+ handler);
		long tiempoInicio = System.currentTimeMillis(); // calculate the time of start
		request.setAttribute("tiempoInicio", tiempoInicio); // save in the request the time of start
		
		Random random = new Random();
		Integer espera = random.nextInt(100); // create a random waiting
		Thread.sleep(espera);
		return true;
		/*
		 * response.sendRedirect(request.getContextPath().concat("/login"));esto es lo que hariamos si por ejemplo cuando de error queremos redirigirlo
		 * return false;
		 */
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(request.getMethod().equalsIgnoreCase("post")) { // con este vemos si la request es del tipo post y si lo es pues hacemos return para no hacer todas las comprobaciones y el tiempo de espera para la peticion post
			return;// postHandle is a void method
		}
		long tiempoFinal = System.currentTimeMillis();
		long tiempoInicio= (Long)request.getAttribute("tiempoInicio"); // take the variable tiempoInicio form the request and cast object to long
		long tiempoTranscurrido = tiempoFinal - tiempoInicio;
				
		if(handler instanceof HandlerMethod && modelAndView != null) { // validacion al nivel de tipo de dato y que no sea nulo
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}
		Logger.info("TiempoTranscurrido...."+tiempoTranscurrido +" milisegundos");
		Logger.info("TiempoTranscurridoInterceptor posthandle saliendo....");
		
		
	}

	
}
