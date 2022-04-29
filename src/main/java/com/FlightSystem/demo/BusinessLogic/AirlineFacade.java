package com.FlightSystem.demo.BusinessLogic;

import com.FlightSystem.demo.DAO.AirlineCompanyDAO;
import com.FlightSystem.demo.DAO.FlightDAO;
import com.FlightSystem.demo.POCO.AirlineCompany;
import com.FlightSystem.demo.POCO.Flight;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirlineFacade extends AnonymousFacade{

    LoginToken loginToken;
    FlightDAO flightDAO = new FlightDAO();
    AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();

    public AirlineFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public AirlineFacade() {
    }

    public List get_my_flights (long id){
       return flightDAO.getFlightsByAirlineId(id);
    }

    public void update_airline (AirlineCompany airlineCompany){
        if(airlineCompany == null){
            System.out.println("Airline company is null");
            return;
        }
        AirlineCompany _airlineCompany = airlineCompanyDAO.getAirlineByUserName(loginToken.name);
        if(_airlineCompany.id == airlineCompany.id){
            airlineCompanyDAO.update(airlineCompany);
        }else{
            System.out.println("Not authorized");
        }
    }

    //Checks if given flight's departure time is before flight's landing time and if flights
    //remaining tickets in greater than zero and add the given flight
    public void add_flight (Flight flight){
        if(flight == null){
            System.out.println("Flight is null");
        }
        AirlineCompany airlineCompany = airlineCompanyDAO.getAirlineByUserName(loginToken.name);
        if(flight.airlineCompanyId == airlineCompany.id){
            if (flight.departureTime.after(flight.landingTime)) {
                System.out.println("Flight's departure time is after flight's landing time ");
                return;
            } else if(flight.departureTime.equals(flight.landingTime)) {
                System.out.println("Flight's departure time equals flight's landing time ");
                return;
            } else if(flight.originCountryId == flight.destinationCountryId){
                System.out.println("Flight's origin country is same as flight's destination country");
                return;
            } else if(flight.remainingTickets < 0){
                System.out.println("You can't add a flight with a negative number of remaining tickets");
                return;
            }
            flightDAO.add(flight);
        }else{
            System.out.println("Not authorized");
        }
    }

    //Checks if given flight's departure time is before flight's landing time and if flights
    //remaining tickets in greater than zero and update the given flight
    public void update_flight (Flight flight) {
        if(flight == null){
            System.out.println("Flight is null");
            return;
        }
        AirlineCompany airlineCompany = airlineCompanyDAO.getAirlineByUserName(loginToken.name);
        if (flight.airlineCompanyId == airlineCompany.id) {
            if (flight.departureTime.after(flight.landingTime)) {
                System.out.println("Flight's departure time is after flight's landing time ");
                return;
            } else if (flight.departureTime.equals(flight.landingTime)) {
                System.out.println("Flight's departure time equals flight's landing time ");
                return;
            } else if (flight.originCountryId == flight.destinationCountryId) {
                System.out.println("Flight's origin country is same as flight's destination country");
                return;
            }else if(flight.remainingTickets < 0){
                System.out.println("You can't edit a flight to a negative number of remaining tickets");
                return;
            }
            flightDAO.update(flight);
        }else{
            System.out.println("Not authorized");
        }
    }

    public void remove_flight (Flight flight){
        if(flight == null){
            System.out.println("Flight is null");
        }
        AirlineCompany airlineCompany = airlineCompanyDAO.getAirlineByUserName(loginToken.name);
        if(flight.airlineCompanyId == airlineCompany.id){
            flightDAO.remove(flight);
        }else{
            System.out.println("Not authorized");
        }
    }

}
