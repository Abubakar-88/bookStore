package com.book1.controller.admin;

import com.book1.entity.Users;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {
    public AdminLoginFilter(){

    }
    public void destroy(){

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("useremail") !=null;
        String loginURI = httpRequest.getContextPath() + "/admin/login";
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        if(loggedIn && (loginRequest || loginPage)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
            dispatcher.forward(request, response);
        }  else if ( loggedIn || loginRequest) {
            System.out.println("user logged in");
            chain.doFilter(request, response);

        } else {
            System.out.println("user not logged in");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void init(FilterConfig fConfiq) throws ServletException {

    }


}
