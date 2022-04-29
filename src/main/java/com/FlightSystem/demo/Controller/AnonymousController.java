package com.FlightSystem.demo.Controller;

import com.FlightSystem.demo.BusinessLogic.AnonymousFacade;
import com.FlightSystem.demo.POCO.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/anonymous")
public class AnonymousController {

    AnonymousFacade anonymousFacade = new AnonymousFacade();

    @PostMapping("/anonymous")
    public void addCustomer(@RequestBody User user, Customer customer){
        anonymousFacade.add_customer(user, customer);
    }

    @GetMapping("/flight")
    public List<Flight> getAllFlights(){
        return anonymousFacade.get_all_flights();
    }

    @GetMapping("/flight/{id}")
    public Flight getFlightById(@PathVariable int id){
        return anonymousFacade.get_flight_by_Id(id);
    }

    @GetMapping("/flight/{originCountryId}/{destinationCountryId}/{date}")
    public List<Flight> getFlightsByParameter(@PathVariable int originCountryId,
                                              @PathVariable int destinationCountryId,
                                              @PathVariable Date date ){
        return anonymousFacade.get_flights_by_parameters(originCountryId,destinationCountryId,date);
    }

    @GetMapping("/airline")
    public List<AirlineCompany> getAllAirlines(){
        return anonymousFacade.get_all_airlines();
    }

    @GetMapping("/airline/{id}")
    public AirlineCompany getAirlineById(@PathVariable int id){
        return anonymousFacade.get_airline_by_Id(id);
    }

    @GetMapping("/airline/{name}/{countryId}/{userId}")
    public AirlineCompany getAirlineByParameter(@PathVariable String name,
                                              @PathVariable int countryId,
                                              @PathVariable long userId ){
        return anonymousFacade.get_airline_by_parameters(name, countryId, userId);
    }

    @GetMapping("/country")
    public List<Country> getAllCountries(){
        return anonymousFacade.get_all_countries();
    }

    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable int id){
        return anonymousFacade.get_country_by_Id(id);
    }




}
