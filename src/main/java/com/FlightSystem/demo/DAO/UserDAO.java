package com.FlightSystem.demo.DAO;

import com.FlightSystem.demo.POCO.User;
import com.FlightSystem.demo.Repository.Repository;
import com.FlightSystem.demo.Utils.ErrorHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.FlightSystem.demo.Utils.ConnectionManager.closeConnections;

public class UserDAO implements DAO{

    List<User> userList;
    ResultSet result = null;
    Repository repository = new Repository();

    @Override
    public Object get(int id) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        User user = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Users\" WHERE \"Users\".\"Id\"=" + id);
            result.next();
            user = new User(result.getInt("Id")
                    ,result.getString("UserName")
                    ,result.getString("Password")
                    ,result.getString("Email")
                    ,result.getInt("User_Role"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return user;
    }

    @Override
    public List getAll() {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        userList = new ArrayList<>();

        try {
            result = stm.executeQuery
                    ("SELECT * FROM \"Users\"");
            while (result.next()) {
                userList.add(new User
                        (result.getInt("Id")
                                ,result.getString("UserName")
                                ,result.getString("Password")
                                ,result.getString("Email")
                                ,result.getInt("User_Role")));
            }
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return userList;
    }

    @Override
    public void add(Object o) {
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        User user = (User) o;

        try {
            stm.executeUpdate("INSERT INTO \"Users\" (\"UserName\",\"Password\",\"Email\",\"User_Role\") " +
                    "VALUES " +
                    "('"+user.userName+"','"+user.password+"','"+user.email+"',"+user.userRole+")");
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
        User user = (User) o;
        long id = user.id;

        try {
           stm.executeUpdate("DELETE from \"Users\" WHERE \"Users\".\"Id\" = "+id);
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
        User user = (User) o;
        long id = user.id;

        try {
           stm.executeUpdate("UPDATE \"Customers\" SET \"UserName\"='"+ user.userName+"'" +
                    ",\"Password\"='"+user.password+"' \"Email\"='"+ user.password+"'\"" +
                    ",\"User_Role\"='"+user.userRole+"' "+" WHERE \"Id\" = "+id+"");
        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
    }

    public User getUserByUserName(String userName){
        Connection connection = repository.getConnection();
        Statement stm = repository.getStatement();
        User user = null;

        try {
            result = stm.executeQuery
                    ("SELECT * FROM get_user_by_username('"+userName+"')");
            result.next();
            user = new User(result.getInt("Id")
                    ,result.getString("UserName")
                    ,result.getString("Password")
                    ,result.getString("Email")
                    ,result.getInt("User_Role"));


        } catch (SQLException e) {
            ErrorHandler.printError(e);
        } finally {
            closeConnections(connection, result, stm);
        }
        return user;
    }
}
