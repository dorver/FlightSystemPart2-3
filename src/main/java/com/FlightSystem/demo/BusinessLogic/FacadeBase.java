package com.FlightSystem.demo.BusinessLogic;

import com.FlightSystem.demo.DAO.*;
import com.FlightSystem.demo.POCO.*;

import java.util.Date;
import java.util.List;

abstract class FacadeBase {

    FlightDAO flightDAO = new FlightDAO();
    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();
    CountryDAO countryDAO = new CountryDAO();
    AdministratorDAO administratorDAO = new AdministratorDAO();

   public List<Flight> get_all_flights() {
        return flightDAO.getAll();
    }

    public Flight get_flight_by_Id(int id) {
        return flightDAO.get(id);
    }

    public List<Flight> get_flights_by_parameters(int origin_country_id, int destination_country_id, Date date){
        return flightDAO.getFlightsByParameters(origin_country_id,destination_country_id,date);
    }

    public List<AirlineCompany> get_all_airlines() {
        return airlineCompanyDAO.getAll();
    }

    public AirlineCompany get_airline_by_Id(int id) {
        return airlineCompanyDAO.get(id);
    }

    public AirlineCompany get_airline_by_parameters(String name, int country_id, long user_id){
        return airlineCompanyDAO.getAirlineByParameters(name,country_id,user_id);
    }

    public List<Country> get_all_countries() {
        return countryDAO.getAll();
    }

    public Country get_country_by_Id(int id) {
        return countryDAO.get(id);
    }

    public void create_new_user(User user, Customer customer, AirlineCompany airlineCompany, Administrator administrator){

       userDAO.add(user);
        if(airlineCompany == null && administrator == null){
           customerDAO.add(customer);

       }else if(customer == null && airlineCompany == null){
            administratorDAO.add(administrator);
        }else{
            airlineCompanyDAO.add(airlineCompany);
        }
    }



}
