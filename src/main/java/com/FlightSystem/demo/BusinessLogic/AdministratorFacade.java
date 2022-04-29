package com.FlightSystem.demo.BusinessLogic;

import com.FlightSystem.demo.DAO.AdministratorDAO;
import com.FlightSystem.demo.DAO.AirlineCompanyDAO;
import com.FlightSystem.demo.DAO.CustomerDAO;
import com.FlightSystem.demo.POCO.Administrator;
import com.FlightSystem.demo.POCO.AirlineCompany;
import com.FlightSystem.demo.POCO.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdministratorFacade extends AnonymousFacade{

    LoginToken loginToken;
    CustomerDAO customerDAO = new CustomerDAO();
    AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();
    AdministratorDAO administratorDAO = new AdministratorDAO();

    public AdministratorFacade() {
    }

    public AdministratorFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public List get_all_customers(){
        return customerDAO.getAll();
    }

    public void add_airline (AirlineCompany airlineCompany){
        if(airlineCompany == null){
            System.out.println("Airline company is null");
            return;
        }
        Administrator administrator = administratorDAO.getAdminByUserName(loginToken.name);
        if(administrator != null){
            airlineCompanyDAO.add(airlineCompany);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void add_customer (Customer customer){
        if(customer == null){
            System.out.println("Customer is null");
            return;
        }
        Administrator administrator = administratorDAO.getAdminByUserName(loginToken.name);
        if(administrator != null){
            customerDAO.add(customer);
        } else{
            System.out.println("Not authorized");
        }
    }

    public void add_administrator(Administrator administrator){
        if(administrator == null){
            System.out.println("Administrator given is null");
            return;
        }
        Administrator _administrator = administratorDAO.getAdminByUserName(loginToken.name);
        if(_administrator != null){
            administratorDAO.add(administrator);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void remove_airline (AirlineCompany airlineCompany){
        if(airlineCompany == null){
            System.out.println("Airline company is null");
            return;
        }
        Administrator administrator = administratorDAO.getAdminByUserName(loginToken.name);
        if(administrator != null){
            airlineCompanyDAO.remove(airlineCompany);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void remove_customer (Customer customer){
        if(customer == null){
            System.out.println("Customer is null");
            return;
        }
        Administrator administrator = administratorDAO.getAdminByUserName(loginToken.name);
        if(administrator != null){
            customerDAO.remove(customer);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void remove_administrator (Administrator administrator) {
        if(administrator == null){
            System.out.println("Administrator is null");
            return;
        }
        Administrator _administrator = administratorDAO.getAdminByUserName(loginToken.name);
        if (_administrator != null) {
            administratorDAO.remove(administrator);
        }else{
            System.out.println("Not authorized");
        }
    }
}
