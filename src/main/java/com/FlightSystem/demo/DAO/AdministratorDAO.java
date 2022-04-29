package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.Administrator;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

@Component
public class AdministratorDAO implements DAO {

    List<Administrator> administratorList;
    ResultSet result = null;
    Repository repository = new Repository();

    //Get and returns admin by id from db
    @Override
    public Object get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Administrator admin = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Administrators\" WHERE \"Administrators\".\"Id\"=" + id);
            result.next();
            admin = new Administrator(result.getInt("Id")
                    , result.getString("First_Name")
                    , result.getString("Last_Name")
                    , result.getInt("User_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return admin;
    }

    //Get and returns all admins from db
    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        administratorList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Administrators\"");
            while (result.next()) {
                administratorList.add(new Administrator
                        (result.getInt("Id")
                                , result.getString("First_Name")
                                , result.getString("Last_Name")
                                , result.getInt("User_Id")));
            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return administratorList;
    }

    //Adds new admin to db
    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Administrator admin = (Administrator) o;

        try {
            stm.executeUpdate("INSERT INTO \"Administrators\" (\"First_Name\",\"Last_Name\",\"User_Id\") " +
                    "VALUES " +
                    "('" + admin.firstName + "','" + admin.lastName + "'," + admin.userId + ")");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Removes admin from db
    @Override
    public void remove(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Administrator admin = (Administrator) o;

        int id = admin.id;
        try {
           stm.executeUpdate("DELETE from \"Administrators\" WHERE \"Administrators\".\"Id\" = " + id);
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Update a given admin in db
    @Override
    public void update(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Administrator admin = (Administrator) o;
        int id = admin.id;

        try {
           stm.executeUpdate("UPDATE \"Administrators\" SET \"First_Name\"='" + admin.firstName + "',\"Last_Name\"='" + admin.lastName + "' " + " WHERE \"Id\" = " + id + "");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    //Get and returns admin by username
    public Administrator getAdminByUserName(String userName) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Administrator administrator = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_admin_by_username('" + userName + "')");
            result.next();
            administrator = new Administrator(result.getInt("Id")
                    , result.getString("First_Name")
                    , result.getString("Last_Name")
                    , result.getInt("User_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return administrator;
    }
}
