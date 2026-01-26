package com.bolsaideas.springboot.app.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	//@Column(name = "nombre_cliente") podemos personalizar el nombre del campo que esta en la base de datos
	private String nombre;
	private String apellido;
	private String email;
	@Column (name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	private static final long serialVersionUID = 1L;  // esto es debido a la interfaz serializable. Esto se hace por que usualmente querremos convertir un objeto a una cadena de bytes para transaportar, guardar, etc
	
	
	public Cliente() {
		
	}

	public Cliente(Long id, String nombre, String apellido, String email, Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	


}
