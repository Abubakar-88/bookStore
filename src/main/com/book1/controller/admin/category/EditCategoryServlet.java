package com.book1.controller.admin.category;

import com.book1.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/edit_category")
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCategoryServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryService categoryServices = new CategoryService(response, request);
        categoryServices.editCategory();
    }

}
