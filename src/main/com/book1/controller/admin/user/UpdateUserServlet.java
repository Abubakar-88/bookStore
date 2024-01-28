package com.book1.controller.admin.user;

import com.book1.service.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/update_user")
public class UpdateUserServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    public UpdateUserServlet(){
        super();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServices userServices = new UserServices(req, resp);
        userServices.udpateUser();
    }




}
