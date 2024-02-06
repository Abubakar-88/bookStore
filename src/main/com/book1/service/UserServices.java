package com.book1.service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.book1.dao.UserDAO;
import com.book1.entity.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServices {
    private UserDAO userDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public UserServices(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        userDAO = new UserDAO();
    }

public void listUser() throws ServletException, IOException {
        listUser(null);
}

public void listUser(String message) throws ServletException, IOException {
        List<Users> listUsers = userDAO.listAll();
        request.setAttribute("listUsers", listUsers);

        if(listUsers != null){
            request.setAttribute("message", message );
        }
        String listPage = "user_list.jsp";
    RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
    requestDispatcher.forward(request, response);


}

public void createUser() throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

    String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    System.out.println("Hashed Password Length: " + hashedPassword.length());
        Users existUser = userDAO.findByEmail(email);

        if(existUser !=null){
            String message = "Could not create user. A user with email " + email   + " already exists";
            request.setAttribute("message", message);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Users newUser = new Users(email, fullName, hashedPassword, phone);
            userDAO.create(newUser);
            listUser("new user created successfully");
        }
}
  public void editUser() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        Users user = userDAO.get(userId);

        String editPage = "user_form.jsp";
        request.setAttribute("user", user);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
        requestDispatcher.forward(request,response);
  }
public void udpateUser() throws ServletException, IOException{
        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        Users userById = userDAO.get(userId);
        Users userByEmail = userDAO.findByEmail(email);
    String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()){
            String message = "Could not update. User with email " + email + " already exists.";
            request.setAttribute("message", message);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Users user = new Users(userId, email, fullName, hashedPassword, phone);
            userDAO.update(user);
            String message = "User has been updated successfully";
            listUser(message);
        }
}


public void deleteUser() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(userId);
        String message = "User has been deleted successfully";
        listUser(message);
    }


    public void login() throws ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        boolean loginResult = userDAO.checkLogin(email, password);

        if(loginResult){
            request.getSession().setAttribute("useremail", email);
            request.getSession().setAttribute("fullName", fullName);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        } else {
            String message = "Login failed!";
            request.setAttribute("message", message);

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}



