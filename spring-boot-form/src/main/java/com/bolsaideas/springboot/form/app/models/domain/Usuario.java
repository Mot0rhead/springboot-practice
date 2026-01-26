package com.bolsaideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.bolsaideas.springboot.form.app.validation.IdentificadorRegex;
import com.bolsaideas.springboot.form.app.validation.Requerido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {
	
	//@Pattern(regexp="[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}") // validation of the variable identificador
	@IdentificadorRegex
	private String identificador;
	//@NotEmpty
	private String nombre;
	//@NotEmpty
	@Requerido
	private String apellido;	
	@NotEmpty
	@Size(min= 3 ,max= 8)
	private String username;
	@NotEmpty
	private String password;
	//@NotEmpty
	@Requerido
	@Email
	private String email;
	
	@NotNull
	@Min(1)
	@Max(10)
	private Integer cuenta;
	
	@NotNull
	@Past
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaNacimiento;
	
	@NotNull
	private Pais pais;
	@NotEmpty
	private List<Role> roles;
	
	private boolean habilitar;
	
	@NotEmpty
	private String genero;
	
	@NotEmpty
	private String valorSecreto;
	
	
	 
	public String getValorSecreto() {
		return valorSecreto;
	}
	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public boolean isHabilitar() {
		return habilitar;
	}
	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
