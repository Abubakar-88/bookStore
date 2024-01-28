package com.book1.controller.admin;

import com.book1.dao.UserDAO;

import com.book1.entity.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1l;
    public AdminHomeServlet(){
        super();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();

        long totalUsers = userDao.count();
        request.setAttribute("totalUsers", totalUsers);
//
        String homePage = "index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
        dispatcher.forward(request, response);

        System.out.println("total user is: " + totalUsers);

        System.out.println("hompageservlet");
    }



}
