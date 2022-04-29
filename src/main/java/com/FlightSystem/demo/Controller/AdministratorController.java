package com.FlightSystem.demo.Controller;

import com.FlightSystem.demo.BusinessLogic.AdministratorFacade;
import com.FlightSystem.demo.BusinessLogic.AirlineFacade;
import com.FlightSystem.demo.BusinessLogic.CustomerFacade;
import com.FlightSystem.demo.POCO.Administrator;
import com.FlightSystem.demo.POCO.AirlineCompany;
import com.FlightSystem.demo.POCO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    private AdministratorFacade administratorFacade;

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return administratorFacade.get_all_customers();
    }

    @PostMapping("/airline")
    public void addAirline(@RequestBody AirlineCompany airlineCompany) {
        administratorFacade.add_airline(airlineCompany);
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {
        administratorFacade.add_customer(customer);
    }

    @PostMapping("/admin")
    public void addAdmin(@RequestBody Administrator administrator) {
        administratorFacade.add_administrator(administrator);
    }

    @DeleteMapping("/airline")
    public void removeAirline(@RequestBody AirlineCompany airlineCompany) {
        administratorFacade.remove_airline(airlineCompany);
    }

    @DeleteMapping("/customer")
    public void removeCustomer(@RequestBody Customer customer) {
        administratorFacade.remove_customer(customer);
    }

    @DeleteMapping("/admin")
    public void removeAdmin(@RequestBody Administrator administrator) {
        administratorFacade.remove_administrator(administrator);
    }

}
