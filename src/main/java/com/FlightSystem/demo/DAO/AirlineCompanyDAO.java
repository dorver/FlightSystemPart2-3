package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.AirlineCompany;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

public class AirlineCompanyDAO implements DAO{

    List<AirlineCompany> airlineCompaniesList;
    ResultSet result = null;
    Repository repository = new Repository();

    //Get and returns airline company by id from db
    @Override
    public AirlineCompany get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        AirlineCompany airlineCompany = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\" WHERE \"Airline_Companies\".\"Id\"=" + id);
            result.next();
            airlineCompany = new AirlineCompany(result.getInt("Id")
                    ,result.getString("Name")
                    ,result.getInt("Country_Id")
                    ,result.getInt("User_Id"));

        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return airlineCompany;
    }

    //Get and returns all airline companies from db
    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        airlineCompaniesList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\"");
            while (result.next()) {
                airlineCompaniesList.add(new AirlineCompany
                        (result.getInt("Id")
                                ,result.getString("Name")
                                ,result.getInt("Country_Id")
                                ,result.getInt("User_Id")));
            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return airlineCompaniesList;
    }

    //Adds new airline company to db
    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        AirlineCompany airlineCompany = (AirlineCompany) o;

        try {
            stm.executeUpdate("INSERT INTO \"Airline_Companies\" (\"Name\",\"Country_Id\",\"User_Id\") " +
                    "VALUES " +
                    "('"+airlineCompany.name+"',"+airlineCompany.countryId+","+airlineCompany.userId+")");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Remove airline id from db
    @Override
    public void remove(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        AirlineCompany airlineCompany = (AirlineCompany) o;
        long id = airlineCompany.id;

        try {
            stm.executeUpdate("DELETE from \"Airline_Companies\" WHERE \"Airline_Companies\".\"Id\" = "+id);
        }
        catch (SQLException e){
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Update a given airline company in db
    @Override
    public void update(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        AirlineCompany airlineCompany = (AirlineCompany) o;
        long id = airlineCompany.id;

        try {
             stm.executeUpdate("UPDATE \"Airline_Companies\" SET \"Name\"='"+ airlineCompany.name+"',\"Country_Id\"="+airlineCompany.countryId+" "+" WHERE \"Id\" = "+id+"");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Get and returns airline companies by country id from db
    public List getAirlinesByCountry(int country_id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        airlineCompaniesList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\" WHERE \"Airline_Companies\".\"Country_Id\"=" + country_id);
            result.next();
            airlineCompaniesList.add(new AirlineCompany
                    (result.getInt("Id")
                            ,result.getString("Name")
                            ,result.getInt("Country_Id")
                            ,result.getInt("User_Id")));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return airlineCompaniesList;
    }

    //Get and returns airline company by username from db
    public AirlineCompany getAirlineByUserName(String userName){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        AirlineCompany airlineCompany = null;

        try {
             result = stm.executeQuery
                    ("SELECT * FROM get_airline_by_username('"+userName+"')");
            result.next();
            airlineCompany = new AirlineCompany(result.getInt("Id")
                    ,result.getString("Name")
                    ,result.getInt("Country_Id")
                    ,result.getInt("User_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return airlineCompany;
    }

    //Get and returns airline company by parameters from db
    public AirlineCompany getAirlineByParameters(String name, int country_id, long userId) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        AirlineCompany airlineCompany = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\" WHERE \"Airline_Companies\".\"Country_Id\"=" + country_id +
                            "\"Airline_Companies\".\"Name\" =" + name +
                            "\"Airline_Companies\".\"UserId\" =" + userId);
            result.next();
            airlineCompany = new AirlineCompany
                    (result.getInt("Id")
                            ,result.getString("Name")
                            ,result.getInt("Country_Id")
                            ,result.getInt("User_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return airlineCompany;
    }
}
