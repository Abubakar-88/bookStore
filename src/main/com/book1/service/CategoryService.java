package com.book1.service;

import com.book1.dao.BookDAO;
import com.book1.dao.CategoryDAO;
import com.book1.entity.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class CategoryService {

    private CategoryDAO categoryDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CategoryService(HttpServletResponse response, HttpServletRequest request){
        this.request = request;
        this.response = response;

        categoryDAO = new CategoryDAO();
    }

    public void listCategory(String message) throws ServletException, IOException {
        List<Category> listCategory = categoryDAO.listAll();

        request.setAttribute("listCategory", listCategory);
        if(message !=null){
            request.setAttribute("message", message);
        }

        String listPage = "category_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }
    public void listCategory() throws ServletException, IOException {
        listCategory(null);
    }
    public void createCategory() throws ServletException, IOException {
        String name = request.getParameter("name"); // name get from html inuput form or query parameter
        Category existCategory = categoryDAO.findByName(name);

        if(existCategory !=null){
            String message = "Could not create category." + "A category with name " + name + " already exists.";

            request.setAttribute("message", message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Category newCategory = new Category(name);
            categoryDAO.create(newCategory);
            String message = "New category created successfully.";
            listCategory(message);
        }
    }


    public void editCategory() throws ServletException, IOException {
       // int categoryId = Integer.parseInt(request.getContextPath());
        int catetoryId = Integer.parseInt(request.getParameter("id"));
       Category category = categoryDAO.get(catetoryId);
       request.setAttribute("category", category);

       String editPage = "category_form.jsp";
       RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
       requestDispatcher.forward(request, response);
    }
    public void updateCategory() throws ServletException, IOException {
        int catetoryId = Integer.parseInt(request.getParameter("categoryId"));
        String categoryName = request.getParameter("name");
        Category categoryById = categoryDAO.get(catetoryId);
        Category categoryByName = categoryDAO.findByName(categoryName);
        if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()){
             String message = "Could no update category." + "A category with name " + categoryName + "already exists.";
             request.setAttribute("message", message);
             RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
             requestDispatcher.forward(request, response);
        }else {
            categoryById.setName(categoryName);
            categoryDAO.update(categoryById);
            String message = "Category has been updated successfully";
            listCategory(message);
        }
    }



//    categoryByName != null:
//
//    This part checks if the variable categoryByName is not null. If categoryByName is null, the condition evaluates to false.
//    If it's not null, the evaluation continues to the next part of the condition.
//            categoryById.getCategoryId() != categoryByName.getCategoryId():
//
//    Assuming categoryById and categoryByName are objects with a method getCategoryId(), this part checks if the categoryId of
//    the categoryById object is not equal to the categoryId of the categoryByName object.
//    If the categoryId values are different or if either categoryById or categoryByName is null, this part evaluates to true.
//    If the categoryId values are the same, it evaluates to false.

//    Combined Condition with && (Logical AND):
//
//    The entire condition is a combination of the two individual conditions using the logical AND (&&) operator.
//    The overall condition is true only if both individual conditions are true. It means:
//    categoryByName is not null, and
//    The categoryId values of categoryById and categoryByName are not equal.

 public void deleteCategory() throws ServletException, IOException {
     int categoryId = Integer.parseInt(request.getParameter("id"));
     BookDAO bookDAO = new BookDAO();
     long numberOfBooks = bookDAO.countByCategory(categoryId);
     String message ;

     if(numberOfBooks >0){
         message = "Could not delete the category (ID:%d) because it currently contains some books";
         message = String.format(message, numberOfBooks);
     } else {
         categoryDAO.delete(categoryId);
         message = "The category with ID "+ categoryId + " has been removed successfully.";
     }

     listCategory(message);
 }

}
