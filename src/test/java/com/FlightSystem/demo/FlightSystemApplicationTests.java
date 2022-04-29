package com.FlightSystem.demo;

import com.FlightSystem.demo.DAO.CustomerDAO;
import com.FlightSystem.demo.DAO.FlightDAO;
import com.FlightSystem.demo.DAO.TicketDAO;
import com.FlightSystem.demo.POCO.Customer;
import com.FlightSystem.demo.POCO.Flight;
import com.FlightSystem.demo.POCO.Ticket;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
class FlightSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getFromAdminControllerTest(){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/admin/customer"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		Customer[] customers = gson.fromJson(response.body(), Customer[].class);

		Customer expected = customers[0];
		CustomerDAO customerDAO = new CustomerDAO();
		Customer current = (Customer) customerDAO.getAll().get(0);

		Assert.assertEquals(current,expected);
	}

	@Test
	void getFromAirlineCompanyControllerTest(){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/airline/flight/1"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		Flight[] flights = gson.fromJson(response.body(), Flight[].class);

		Flight expected = flights[0];
		FlightDAO flightDAO = new FlightDAO();
		Flight current = (Flight) flightDAO.getAll().get(0);

		Assert.assertEquals(current,expected);
	}

	@Test
	void getFromAnonymousControllerTest(){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/anonymous/flight"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		Flight[] flights = gson.fromJson(response.body(), Flight[].class);

		Flight expected = flights[0];
		FlightDAO flightDAO = new FlightDAO();
		Flight current = (Flight) flightDAO.getAll().get(0);

		Assert.assertEquals(current,expected);
	}

	@Test
	void getFromCustomerControllerTest(){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/customer/ticket/1"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		Ticket[] tickets = gson.fromJson(response.body(), Ticket[].class);

		Ticket expected = tickets[0];
		TicketDAO ticketDAO = new TicketDAO();
		Ticket current = (Ticket) ticketDAO.getAll().get(0);

		Assert.assertEquals(current,expected);
	}

}

