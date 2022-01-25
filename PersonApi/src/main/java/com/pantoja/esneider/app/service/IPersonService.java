package com.pantoja.esneider.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pantoja.esneider.app.dto.PersonDTO;

public interface IPersonService {
	
	public List<PersonDTO> findAll(Sort sort);
	public Page<PersonDTO> findAll(Pageable pageable);
	public PersonDTO findById(long id);
	public String delete(long id);
	public PersonDTO save(PersonDTO personDTO);

}
