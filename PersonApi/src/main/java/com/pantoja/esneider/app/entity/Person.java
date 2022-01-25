package com.pantoja.esneider.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pantoja.esneider.app.util.EstadoEmpleado;

@Table
@Entity
public class Person implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nombre")
	@NotEmpty(message = "el campo nombre no puede estar vacio")
	@Size(min = 4, max = 255, message = "El campo nombre debe tener entre 4-255 caracteres")
	private String nombre;
	
	@Column(name = "apellido")
	@Size(min = 4, max = 255, message = "El campo apellido debe tener entre 4-255 caracteres")
	private String apellido;
	
	@Column(name = "dni", unique = true)
	@Size(min = 4, max = 255, message = "El campo dni debe tener entre 4-255 caracteres")
	private String dni;
	
	@Column(name = "is_empleado")
	@Enumerated(EnumType.STRING)
	private EstadoEmpleado isEmpleado;

	public Person() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public EstadoEmpleado getIsEmpleado() {
		return isEmpleado;
	}

	public void setIsEmpleado(EstadoEmpleado isEmpleado) {
		this.isEmpleado = isEmpleado;
	}

	
	}
