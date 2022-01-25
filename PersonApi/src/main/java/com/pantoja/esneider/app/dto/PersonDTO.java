package com.pantoja.esneider.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pantoja.esneider.app.util.EstadoEmpleado;

public class PersonDTO {
	
	private long id;
	
	@Size(min = 4, max = 255 ,message = "Campo nombre debe tener un valor mayor ó igual a 4 digitos")
	@NotNull(message = "Campo nombre es obligatorio")
	private String nombre;
	
	@Size(min = 4, max = 255 ,message = "Campo apellido debe tener un valor mayor ó igual a 4 digitos")
	@NotNull(message = "Campo apellido es obligatorio")
	private String apellido;
	
	@NotNull(message = "Campo dni debe tener un valor mayor ó igual a 4 digitos")
	private String dni;
	
	@NotNull(message = "Campo isEmpleado debe tener uno de los siguientes valores, [SI, NO]")
	//@Size( max = 2 ,message = "Campo isEmpleado debe tener un valor igual a 2 digitos")
	private EstadoEmpleado isEmpleado;
	
	
	public PersonDTO() {
	}


	public PersonDTO(long id, String nombre, String apellido, String dni, EstadoEmpleado isEmpleado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.isEmpleado = isEmpleado;
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
