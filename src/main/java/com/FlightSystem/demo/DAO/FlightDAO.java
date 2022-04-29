package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.Flight;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

public class FlightDAO implements DAO{

    List<Flight> flightList;
    ResultSet result = null;
    Repository repository = new Repository();

    //Get and returns flight by id from db
    @Override
    public Flight get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Flight flight = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Flights\" WHERE \"Flights\".\"Id\"=" + id);
            result.next();
            flight = new Flight(result.getInt("Id")
                    ,result.getInt("Airline_Company_Id")
                    ,result.getInt("Origin_Country_Id")
                    ,result.getInt("Destination_Country_Id")
                    ,result.getTimestamp("Departure_Time")
                    ,result.getTimestamp("Landing_Time")
                    ,result.getInt("Remaining_Tickets"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flight;
    }

    //Get and returns all flights from db
    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Flights\"");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        ,result.getInt("Airline_Company_Id")
                        ,result.getInt("Origin_Country_Id")
                        ,result.getInt("Destination_Country_Id")
                        ,result.getTimestamp("Departure_Time")
                        ,result.getTimestamp("Landing_Time")
                        ,result.getInt("Remaining_Tickets")));

            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Add new flight to db
    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Flight flight = (Flight) o;

        try {
            stm.executeUpdate("INSERT INTO \"Flights\" (\"Airline_Company_Id\",\"Origin_Country_Id\",\"Destination_Country_Id\",\"Departure_Time\",\"Landing_Time\",\"Remaining_Tickets\") VALUES("+flight.airlineCompanyId+","+flight.originCountryId+","+flight.destinationCountryId+",'"+flight.departureTime+"','"+flight.landingTime+"',"+flight.remainingTickets+")");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Removes flight from db
    @Override
    public void remove(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Flight flight = (Flight) o;
        long id = flight.id;

        try {
            stm.executeUpdate("DELETE from \"Flights\" WHERE \"Flights\".\"Id\" = "+id);
        }
        catch (SQLException e){
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Update a given flight in db
    @Override
    public void update(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Flight flight = (Flight) o;
        long id = flight.id;

        try {
            stm.executeUpdate("UPDATE \"Flights\" SET \"Airline_Company_Id\"="+ flight.airlineCompanyId+",\"Origin_Country_Id\"="+flight.originCountryId+", \"Destination_Country_Id\"="+ flight.destinationCountryId+",\"Departure_Time\"='"+flight.departureTime+"', \"Landing_Time\"='"+ flight.landingTime+"', \"Remaining_Tickets\"="+ flight.remainingTickets+" "+" WHERE \"Id\" = "+id+"");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Get and returns all flights by given origin country id from db
    public List getFlightsByOriginCountryId(int country_id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Flights\" WHERE \"Flights\".\"Origin_Country_Id\"=" + country_id);
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getTimestamp("Departure_Time")
                        , result.getTimestamp("Landing_Time")
                        , result.getInt("Remaining_Tickets")));
            }

        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all flights by given destination country id from db
    public List getFlightsByDestinationCountryId(int country_id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Flights\" WHERE \"Flights\".\"Destination_Country_Id\"=" + country_id);
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getTimestamp("Departure_Time")
                        , result.getTimestamp("Landing_Time")
                        , result.getInt("Remaining_Tickets")));
            }

        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all flights in the same date as given departure date from db
    public List getFlightsByDepartureDate(Date date) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_flights_by_departure_date("+date+")");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getTimestamp("Departure_Time")
                        , result.getTimestamp("Landing_Time")
                        , result.getInt("Remaining_Tickets")));
            }

        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all flights in the same date as given landing date from db
    public List getFlightsByLandingDate(Timestamp date) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_flights_by_landing_date("+date+")");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getTimestamp("Departure_Time")
                        , result.getTimestamp("Landing_Time")
                        , result.getInt("Remaining_Tickets")));
            }


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all customer's flights by its customer id from db
    public List getFlightsByCustomer(long customerId) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_flights_by_customer("+customerId+")");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getTimestamp("Departure_Time")
                        , result.getTimestamp("Landing_Time")
                        , result.getInt("Remaining_Tickets")));
            }

        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all flights by given parameters from db
    public List getFlightsByParameters(int originCountryId, int destinationCountryId, Date date){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        List<Flight> flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_flights_by_parameters("+originCountryId+","+destinationCountryId+",'"+date+"')");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        ,result.getInt("Airline_Company_Id")
                        ,result.getInt("Origin_Country_Id")
                        ,result.getInt("Destination_Country_Id")
                        ,result.getTimestamp("Departure_Time")
                        ,result.getTimestamp("Landing_Time")
                        ,result.getInt("Remaining_Tickets")));

            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all flights by given airline id from db
    public List getFlightsByAirlineId(long airlineId){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        List<Flight> flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_flights_by_airline_id("+airlineId+")");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        ,result.getInt("Airline_Company_Id")
                        ,result.getInt("Origin_Country_Id")
                        ,result.getInt("Destination_Country_Id")
                        ,result.getTimestamp("Departure_Time")
                        ,result.getTimestamp("Landing_Time")
                        ,result.getInt("Remaining_Tickets")));

            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all arrival flights in the next 12 hours by country id from db
    public List getArrivalFlights(int countryId){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        List<Flight> flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_arrival_flights("+countryId+")");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        ,result.getInt("Airline_Company_Id")
                        ,result.getInt("Origin_Country_Id")
                        ,result.getInt("Destination_Country_Id")
                        ,result.getTimestamp("Departure_Time")
                        ,result.getTimestamp("Landing_Time")
                        ,result.getInt("Remaining_Tickets")));

            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

    //Get and returns all departure flights in the next 12 hours by country id from db
    public List getDepartureFlights(int countryId){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        List<Flight> flightList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_departure_flights("+countryId+")");
            while (result.next()) {
                flightList.add(new Flight(result.getInt("Id")
                        ,result.getInt("Airline_Company_Id")
                        ,result.getInt("Origin_Country_Id")
                        ,result.getInt("Destination_Country_Id")
                        ,result.getTimestamp("Departure_Time")
                        ,result.getTimestamp("Landing_Time")
                        ,result.getInt("Remaining_Tickets")));

            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return flightList;
    }

}
