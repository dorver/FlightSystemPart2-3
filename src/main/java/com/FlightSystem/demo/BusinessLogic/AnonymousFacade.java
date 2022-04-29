package com.FlightSystem.demo.BusinessLogic;

import com.FlightSystem.demo.DAO.UserDAO;
import com.FlightSystem.demo.POCO.Customer;
import com.FlightSystem.demo.POCO.User;

import java.util.List;

public class AnonymousFacade extends FacadeBase {

    UserDAO userDAO = new UserDAO();
    LoginToken loginToken;

    //Login a user and returns a matching facade with a login token
    public AnonymousFacade login(String userName, String password) {
        List<User> userList = userDAO.getAll();
        for (var user : userList) {
            if (user.userName.equals(userName) && user.password.equals(password)) {
                switch (user.userRole) {
                    case 1:
                        return new CustomerFacade(new LoginToken(user.id, user.userName, "customer"));
                    case 2:
                        return new AirlineFacade(new LoginToken(user.id, user.userName, "airline"));
                    case 3:
                        return new AdministratorFacade(new LoginToken(user.id, user.userName, "admin"));
                }
            }
        }


        System.out.println("Wrong username or password");
        return null;
    }

   //Regsiter new user and customer
   public void add_customer(User user, Customer customer) {
        if (user == null){
            System.out.println("User is null");
            return;
        }
        if(customer == null) {
            System.out.println("Customer is null");
            return;
        }
        this.create_new_user(user, customer, null, null);
    }


}
