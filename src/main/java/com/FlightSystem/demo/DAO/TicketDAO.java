package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.Ticket;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

public class TicketDAO implements DAO{

    List<Ticket> ticketList;
    ResultSet result = null;
    Repository repository = new Repository();

    @Override
    public Ticket get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Ticket ticket = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Tickets\" WHERE \"Tickets\".\"Id\"=" + id);
            result.next();
            ticket = new Ticket(result.getInt("Id")
                    ,result.getInt("Flight_Id")
                    ,result.getInt("Customer_Id"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return ticket;
    }

    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        ticketList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Tickets\"");
            while (result.next()) {
                ticketList.add(new Ticket
                        (result.getInt("Id")
                                ,result.getInt("Flight_Id")
                                ,result.getInt("Customer_Id")));
            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return ticketList;
    }

    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Ticket ticket = (Ticket) o;

        try {
            stm.executeUpdate("INSERT INTO \"Tickets\" (\"Flight_Id\", \"Customer_Id\") " +
                    "VALUES " +
                    "("+ticket.flightId+","+ticket.customerId+")");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    @Override
    public void remove(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Ticket ticket = (Ticket) o;
        long id = ticket.id;

        try {
            stm.executeUpdate("DELETE from \"Tickets\" WHERE \"Tickets\".\"Id\" = "+id);
        }
        catch (SQLException e){
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    @Override
    public void update(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        Ticket ticket = (Ticket) o;
        long id = ticket.id;

        try {
           stm.executeUpdate("UPDATE \"Tickets\" SET \"Flight_Id\"="+ ticket.flightId+"\"Customer_Id\"="+ticket.customerId+" "+" WHERE \"Id\" = "+id+"");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    public List getTicketsByCustomer(long customerId){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        ticketList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_tickets_by_customer("+customerId+")");
            while (result.next()) {
                ticketList.add(new Ticket
                        (result.getInt("Id")
                                ,result.getInt("Flight_Id")
                                ,result.getInt("Customer_Id")));
            }


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return ticketList;
    }
}
