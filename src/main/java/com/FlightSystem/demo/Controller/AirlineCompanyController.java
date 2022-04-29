package com.FlightSystem.demo.Controller;


import com.FlightSystem.demo.BusinessLogic.AirlineFacade;
import com.FlightSystem.demo.POCO.AirlineCompany;
import com.FlightSystem.demo.POCO.Customer;
import com.FlightSystem.demo.POCO.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineCompanyController {

    @Autowired
    private AirlineFacade airlineFacade;

    @GetMapping("/flight/{id}")
    public List<Flight> getMyFlights(@PathVariable int id){
        return airlineFacade.get_my_flights(id);
    }

    @PutMapping("/airline")
    public void updateAirline(@RequestBody AirlineCompany airlineCompany){
        airlineFacade.update_airline(airlineCompany);
    }

    @PostMapping("/flight")
    public void addFlight(@RequestBody Flight flight){
        airlineFacade.add_flight(flight);
    }

    @PutMapping("/flight")
    public void updateFlight(@RequestBody Flight flight){
        airlineFacade.update_flight(flight);
    }

    @DeleteMapping("/flight")
    public void removeFlight(@RequestBody Flight flight) {
        airlineFacade.remove_flight(flight);
    }
}
