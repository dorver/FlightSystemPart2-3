package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.Country;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

public class CountryDAO implements DAO{

    List<Country> countryList;
    ResultSet result = null;
    Repository repository = new Repository();

    //Get and returns country by id from db
    @Override
    public Country get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Country country = null;

        try {
             result = stm.executeQuery
                    ("SELECT * FROM \"Countries\" WHERE \"Countries\".\"Id\"=" + id);
            result.next();
            country = new Country(result.getInt("Id")
                    ,result.getString("Name"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return country;
    }

    //Get and returns all countries from db
    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        countryList = new ArrayList<>();

        try {
             result = stm.executeQuery
                    ("SELECT * FROM \"Countries\"");
            while (result.next()) {
                countryList.add(new Country
                        (result.getInt("Id")
                                ,result.getString("Name")));
            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return countryList;
    }

    //Add new country to db
    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Country country = (Country) o;

        try {
            stm.executeUpdate("INSERT INTO \"Countries\" (\"Name\") " +
                    "VALUES " +
                    "('"+country.name+"')");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Removes country from db
    @Override
    public void remove(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Country country = (Country) o;
        long id = country.id;

        try {
            stm.executeUpdate("DELETE from \"Countries\" WHERE \"Countries\".\"Id\" = "+id);
        }
        catch (SQLException e){
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Update a given country in db
    @Override
    public void update(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Country country = (Country) o;

        long id = country.id;
        try {
            stm.executeUpdate("UPDATE \"Countries\" SET \"Name\"='"+ country.name+"' "+" WHERE \"Id\" = "+id+"");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }
}
