package com.book1.controller.admin.user;

import com.book1.service.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.plaf.PanelUI;
import java.io.IOException;

@WebServlet("/admin/list_users")
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID =1L;

    public ListUsersServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UserServices userServices = new UserServices(request, response);
       userServices.listUser();
    }
}
