package com.FlightSystem.demo.Controller;

import com.FlightSystem.demo.BusinessLogic.AirlineFacade;
import com.FlightSystem.demo.BusinessLogic.CustomerFacade;
import com.FlightSystem.demo.POCO.Customer;
import com.FlightSystem.demo.POCO.Flight;
import com.FlightSystem.demo.POCO.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerFacade customerFacade;

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody Customer customer){
        customerFacade.update_customer(customer);
    }

    @PostMapping("/ticket")
    public void addFlight(@RequestBody Ticket ticket){
        customerFacade.add_ticket(ticket);
    }

    @DeleteMapping("/ticket")
    public void removeTicket(@RequestBody Ticket ticket) {
        customerFacade.remove_ticket(ticket);
    }

    @GetMapping("/ticket/{id}")
    public List<Ticket> getMyTickets(@PathVariable long id){
        return customerFacade.get_my_tickets(id);
    }


}
