package com.book1.controller.admin.category;

import com.book1.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/create_category")
public class CreateCategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public CreateCategoryServlet() {
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    CategoryService categoryService = new CategoryService(response, request);
    categoryService.createCategory();
}
}
