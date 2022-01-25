package com.pantoja.esneider.app.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pantoja.esneider.app.dao.IPersonDao;
import com.pantoja.esneider.app.dto.PersonDTO;
import com.pantoja.esneider.app.entity.Person;
import com.pantoja.esneider.app.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonDao personDao;

	@Override
	@Transactional(readOnly = true)
	public List<PersonDTO> findAll(Sort sort) {

		List<PersonDTO> dtos = new LinkedList<>();

		List<Person> rst = personDao.findAll(sort);

		if (rst != null && rst.size() > 0) {

			for (Person tmp : rst) {
				dtos.add(generatedPersonDTO(tmp));
			}
		}

		return dtos;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PersonDTO> findAll(Pageable pageable) {

		List<PersonDTO> dtos = new LinkedList<>();
		Page<PersonDTO> pagePersonDTO = null;
		Page<Person> rst = personDao.findAll(pageable);

		if (rst != null) {

			for (Person tmp : rst) {
				dtos.add(generatedPersonDTO(tmp));
			}

			pagePersonDTO = new PageImpl<PersonDTO>(dtos, pageable, rst.getTotalElements());
		}

		return pagePersonDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public PersonDTO findById(long id) {
		Person person = personDao.findById(id);

		if (person != null) {
			return generatedPersonDTO(person);
		}

		return null;
	}

	@Override
	@Transactional
	public String delete(long id) {

		String rst = null;

		try {

			PersonDTO dto = findById(id);

			if (dto != null) {
				personDao.deleteById(dto.getId());
				rst = "1";
			}

		} catch (Exception e) {
			return null;
		}
		return rst;

	}

	@Override
	@Transactional
	public PersonDTO save(PersonDTO personDTO) {
		PersonDTO dto = null;

		Person person = personDao.save(generatedPersonEntity(personDTO));

		if (person != null) {
			return generatedPersonDTO(person);
		}

		return dto;
	}

	private PersonDTO generatedPersonDTO(Person person) {
		return new PersonDTO(person.getId(), person.getNombre(), person.getApellido(), person.getDni(),
				person.getIsEmpleado());
	}

	private Person generatedPersonEntity(PersonDTO dto) {
		Person person = new Person();

		person.setNombre(dto.getNombre().toUpperCase());
		person.setApellido(dto.getApellido().toUpperCase());
		person.setDni(dto.getDni().toUpperCase());
		person.setIsEmpleado(dto.getIsEmpleado());

		return person;
	}
	


}
