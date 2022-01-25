package com.pantoja.esneider.app.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pantoja.esneider.app.dto.ErroDTO;
import com.pantoja.esneider.app.dto.PersonDTO;
import com.pantoja.esneider.app.dto.ResultadoGenericDTO;
import com.pantoja.esneider.app.service.IPersonService;
import com.pantoja.esneider.app.util.EstadoService;

@CrossOrigin("*/*")
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

	@Autowired
	private IPersonService personService;

	@GetMapping({ "", "all", "/" })
	public ResponseEntity<Object> findAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		List<ErroDTO> erors = new LinkedList<>();
		try {

			List<PersonDTO> dtos;
			Sort sortByName = Sort.by("nombre");

			if (page != null && size != null) {
				Pageable pageable = PageRequest.of(page, size, sortByName);
				dtos = personService.findAll(pageable).getContent();
			} else {
				dtos = personService.findAll(sortByName);
			}

			if (dtos != null && dtos.size() > 0) {
				ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.SUCCESS,
						String.valueOf(HttpStatus.OK.value()), dtos);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
			} else {
				erors.add(new ErroDTO(0, "No se encontraron registros"));
				ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.ERROR,
						String.valueOf(HttpStatus.NOT_FOUND.value()), erors, "");
				return new ResponseEntity<Object>(dto, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			erors.add(new ErroDTO(0, e.getMessage()));
			ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), erors, "");
			return new ResponseEntity<Object>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable(required = true, name = "id") long id) {

		ResponseEntity<Object> responseEntity;
		List<ErroDTO> erors = new LinkedList<>();

		try {

			PersonDTO dto = personService.findById(id);

			if (dto != null) {
				ResultadoGenericDTO dtog = new ResultadoGenericDTO(EstadoService.SUCCESS,
						String.valueOf(HttpStatus.OK.value()), dto);
				responseEntity = new ResponseEntity<Object>(dtog, HttpStatus.OK);
			} else {
				erors.add(new ErroDTO(0, "Registro con Id " + id + " no encontrado."));
				ResultadoGenericDTO dtog = new ResultadoGenericDTO(EstadoService.ERROR,
						String.valueOf(HttpStatus.NOT_FOUND.value()), erors, "");
				responseEntity = new ResponseEntity<Object>(dtog, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			erors.add(new ErroDTO(0, e.getMessage()));
			ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), erors, "");
			responseEntity = new ResponseEntity<Object>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@DeleteMapping({ "/delete/{id}" })
	public ResponseEntity<Object> delete(@PathVariable(name = "id", required = true) long id) {

		ResponseEntity<Object> responseEntity = null;
		List<ErroDTO> erors = new LinkedList<>();

		try {

			String rst = personService.delete(id);

			if (rst != null) {
				ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.SUCCESS,
						String.valueOf(HttpStatus.OK.value()));
				responseEntity = new ResponseEntity<Object>(dto, HttpStatus.OK);
			} else {
				erors.add(new ErroDTO(0, "Registro no existente."));
				ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.ERROR,
						String.valueOf(HttpStatus.NOT_FOUND.value()), erors, "");
				responseEntity = new ResponseEntity<Object>(dto, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			erors.add(new ErroDTO(0, e.getMessage()));
			ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), erors, "");
			responseEntity = new ResponseEntity<Object>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@PostMapping(value = "/insert")
	public ResponseEntity<Object> insert(@Valid @RequestBody PersonDTO personDTO, BindingResult result) {

		ResponseEntity<Object> responseEntity = null;
		List<ErroDTO> erors = new LinkedList<>();
		int count = 0;

		try {

			if (result.hasErrors()) {
				for (ObjectError tmp : result.getAllErrors()) {
					erors.add(new ErroDTO(count, tmp.getDefaultMessage()));
					count++;
				}
			}

			if (erors != null && erors.size() > 0) {
				ResultadoGenericDTO dto = new ResultadoGenericDTO(EstadoService.ERROR,
						String.valueOf(HttpStatus.BAD_REQUEST.value()), erors, "");

				responseEntity = new ResponseEntity<Object>(dto, HttpStatus.BAD_REQUEST);
			} else {

				if (personDTO != null) {
					
					PersonDTO dto = personService.save(personDTO);
					
					if (dto != null) {
						ResultadoGenericDTO dtog = new ResultadoGenericDTO(EstadoService.SUCCESS,
								String.valueOf(HttpStatus.CREATED.value()), dto);

						responseEntity = new ResponseEntity<Object>(dtog, HttpStatus.CREATED);
					} else {
						erors.add(new ErroDTO(0, "El registro no se creo satisfactoriamente"));
						ResultadoGenericDTO dtog = new ResultadoGenericDTO(EstadoService.ERROR,
								String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), erors, "");

						responseEntity = new ResponseEntity<Object>(dtog, HttpStatus.INTERNAL_SERVER_ERROR);
					}

				} else {

					erors.add(new ErroDTO(0, "El payload enviado no es valido."));
					ResultadoGenericDTO dtog = new ResultadoGenericDTO(EstadoService.ERROR,
							String.valueOf(HttpStatus.BAD_REQUEST.value()), erors, "");

					responseEntity = new ResponseEntity<Object>(dtog, HttpStatus.BAD_REQUEST);

				}

			}

		} catch (DataAccessException e) {

			erors.add(
					new ErroDTO(0, "El registro no se creo satisfactoriamente " + e.getMostSpecificCause().toString()));
			ResultadoGenericDTO dtog = new ResultadoGenericDTO(EstadoService.SUCCESS,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), erors, "");
			responseEntity = new ResponseEntity<Object>(dtog, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
	
	
	

}
