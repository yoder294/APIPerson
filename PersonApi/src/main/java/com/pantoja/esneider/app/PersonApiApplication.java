package com.pantoja.esneider.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PersonApiApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(PersonApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Person (nombre,apellido,dni,is_empleado) ");
		sql.append(" VALUES ('JUAN','PEREZ','1004598502','SI'),");
		sql.append("('CARLOS','DIAZ','1064598503','NO');");

		template.execute(sql.toString());

	}

}
