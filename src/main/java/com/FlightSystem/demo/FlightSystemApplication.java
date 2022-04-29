package com.FlightSystem.demo;

import com.FlightSystem.demo.DAO.AdministratorDAO;
import com.FlightSystem.demo.POCO.Administrator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightSystemApplication{

	public static void main(String[] args) {

		var context =SpringApplication.run(FlightSystemApplication.class, args);

//		Administrator administrator = new Administrator(4, "admin", "admin", 4);
//
//		var administratorDAO=context.getBean(AdministratorDAO.class);
//
//		administratorDAO.add(administrator);

	}
}

