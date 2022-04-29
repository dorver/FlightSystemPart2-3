package com.FlightSystem.demo.BusinessLogic;

import com.FlightSystem.demo.DAO.CustomerDAO;
import com.FlightSystem.demo.DAO.TicketDAO;
import com.FlightSystem.demo.POCO.Customer;
import com.FlightSystem.demo.POCO.Flight;
import com.FlightSystem.demo.POCO.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFacade extends AnonymousFacade{

    LoginToken loginToken;
    CustomerDAO customerDAO = new CustomerDAO();
    TicketDAO ticketDAO = new TicketDAO();

    public CustomerFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public CustomerFacade() {
    }

    public void update_customer(Customer customer){
        if(customer == null){
            System.out.println("Customer is null");
            return;
        }
        Customer _customer = customerDAO.getCustomerByUserName(loginToken.name);
        if(_customer.id == customer.id){
            customerDAO.update(customer);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void add_ticket(Ticket ticket){
        if(ticket == null){
            System.out.println("Ticket is null");
            return;
        }
        Customer customer = customerDAO.getCustomerByUserName(loginToken.name);
        if(ticket.customerId == customer.id){
            ticketDAO.add(ticket);
            Flight flight = flightDAO.get((int) ticket.flightId);
            Flight updatedFlight = new Flight(flight.id,flight.airlineCompanyId,flight.originCountryId,flight.destinationCountryId,flight.departureTime,flight.landingTime,flight.remainingTickets-1);
            flightDAO.update(updatedFlight);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void remove_ticket(Ticket ticket){
        if(ticket == null){
            System.out.println("Ticket is null");
            return;
        }
        Customer customer = customerDAO.getCustomerByUserName(loginToken.name);
        if(ticket.customerId == customer.id){
            ticketDAO.remove(ticket);
            Flight flight = flightDAO.get((int) ticket.flightId);
            Flight updatedFlight = new Flight(flight.id,flight.airlineCompanyId,flight.originCountryId,flight.destinationCountryId,flight.departureTime,flight.landingTime,flight.remainingTickets+1);
            flightDAO.update(updatedFlight);
        }else{
            System.out.println("Not authorized");
        }
    }

    public List get_my_tickets(long id){
        return ticketDAO.getTicketsByCustomer(id);
    }
}
