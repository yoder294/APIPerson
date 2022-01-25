package com.pantoja.esneider.app.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pantoja.esneider.app.util.EstadoService;

public class ResultadoGenericDTO {
	
	@NotNull
	private EstadoService status;
	@NotNull
	private String code;
	@JsonInclude(value = Include.NON_NULL)
	private PersonDTO person;
	@JsonInclude(value = Include.NON_NULL)
	private List<PersonDTO> persons;
	@JsonInclude(value = Include.NON_EMPTY)
	private List<ErroDTO> errores;
		
	public ResultadoGenericDTO(EstadoService status, String code) {
		this.status = status;
		this.code = code;
	}


	public ResultadoGenericDTO( EstadoService status, String code, List<ErroDTO> errores, String e) {
		this(status,code);
		this.errores = errores;
	}


	public ResultadoGenericDTO(EstadoService status, String code, List<PersonDTO> dtos) {
		this(status,code);
		this.persons = dtos;
	}
	
	public ResultadoGenericDTO(EstadoService status, String code, PersonDTO dto) {
		this(status,code);
		this.person = dto;
	}
	
	
	public EstadoService getStatus() {
		return status;
	}

	public void setStatus(EstadoService status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public PersonDTO getPerson() {
		return person;
	}


	public void setPerson(PersonDTO person) {
		this.person = person;
	}


	public List<PersonDTO> getPersons() {
		return persons;
	}


	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
	}

	
	public List<ErroDTO> getErrores() {
		return errores;
	}



	public void setErrores(List<ErroDTO> errores) {
		this.errores = errores;
	}



}
