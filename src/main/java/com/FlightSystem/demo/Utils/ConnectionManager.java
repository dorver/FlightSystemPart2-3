package com.FlightSystem.demo.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionManager {

    //close result, statement, connection.
    public static void closeConnections(Connection connection, ResultSet result, Statement stm){
        try { if(result != null) result.close(); } catch (Exception e) {e.getMessage();}
        try { if(stm != null) stm.close(); } catch (Exception e) {e.getMessage();}
        try { if(connection != null) connection.close(); } catch (Exception e) {e.getMessage();}
    }
}
