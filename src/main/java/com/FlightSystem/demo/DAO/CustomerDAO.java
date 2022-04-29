package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.Customer;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

public class CustomerDAO implements DAO{

    List<Customer> customerList;
    ResultSet result = null;
    Repository repository = new Repository();

    //Get and returns customer by id from db
    @Override
    public Customer get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Customer customer = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Customers\" WHERE \"Customers\".\"Id\"=" + id);
            result.next();
            customer = new Customer(result.getInt("Id")
                    ,result.getString("First_Name")
                    ,result.getString("Last_Name")
                    ,result.getString("Address")
                    ,result.getString("Phone_No")
                    ,result.getString("Credit_Card_No")
                    ,result.getInt("User_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return customer;
    }

    //Get and returns all Customers from db
    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        customerList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Customers\"");
            while (result.next()) {
                customerList.add(new Customer
                        (result.getInt("Id")
                                ,result.getString("First_Name")
                                ,result.getString("Last_Name")
                                ,result.getString("Address")
                                ,result.getString("Phone_No")
                                ,result.getString("Credit_Card_No")
                                ,result.getInt("User_Id")));
            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return customerList;
    }

    //Adds new customer to db
    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Customer customer = (Customer) o;

        try {
            stm.executeUpdate("INSERT INTO \"Customers\" (\"First_Name\",\"Last_Name\",\"Address\",\"Phone_No\",\"Credit_Card_No\",\"User_Id\") " +
                    "VALUES " +
                    "('"+customer.firstName+"','"+customer.lastName+"','"+customer.address+"','"+customer.phoneNo+"','"+customer.creditCardNo+"',"+customer.userId+")");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Removes customer from db
    @Override
    public void remove(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Customer customer = (Customer) o;
        long id = customer.id;

        try {
            stm.executeUpdate("DELETE from \"Customers\" WHERE \"Customers\".\"Id\" = "+id);
        }
        catch (SQLException e){
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Update a given customer in db
    @Override
    public void update(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Customer customer = (Customer) o;
        long id = customer.id;

        try {
            stm.executeUpdate("UPDATE \"Customers\" SET \"First_Name\"='"+ customer.firstName+"',\"Last_Name\"='"+customer.lastName+"', \"Address\"='"+ customer.address+"',\"Phone_No\"='"+customer.phoneNo+"', \"Credit_Card_No\"='"+ customer.creditCardNo+"' "+" WHERE \"Id\" = "+id+"");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Get and returns customer by username
    public Customer getCustomerByUserName(String userName){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Customer customer = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_customer_by_username('"+userName+"')");
            result.next();
            customer = new Customer(result.getInt("Id")
                    ,result.getString("First_Name")
                    ,result.getString("Last_Name")
                    ,result.getString("Address")
                    ,result.getString("Phone_No")
                    ,result.getString("Credit_Card_No")
                    ,result.getInt("User_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return customer;
    }
}
