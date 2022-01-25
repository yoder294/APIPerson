package com.pantoja.esneider.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pantoja.esneider.app.entity.Person;

public interface IPersonDao  extends JpaRepository<Person, Long> {
	
	@Query(value = "select p from Person p")
	public List<Person> findAll(Sort sort);
	
	@Query(value = "select p from Person p", 
		   countQuery = "select count(p) from Person p")
	public Page<Person> findAll(Pageable pageable);
	
	@Query(value = "select p from Person p where p.id = :id")
	public Person findById(long id);

}
