package com.book1.service;

import com.book1.dao.BookDAO;
import com.book1.dao.CategoryDAO;
import com.book1.entity.Book;
import com.book1.entity.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookServices {

    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BookServices(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        bookDAO = new BookDAO();
        categoryDAO = new CategoryDAO();
    }

    public void listBooks() throws ServletException, IOException {
        listBooks(null);
    }

    public void listBooks(String message) throws ServletException, IOException {
        List<Book> listBooks = bookDAO.listAll();
        request.setAttribute("listBooks", listBooks);
        if(message !=null){
            request.setAttribute("message", message);
        }

        String listPage = "book_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }

    public void showBookNewForm() throws ServletException, IOException {
        List<Category> listCategory = categoryDAO.listAll();
        request.setAttribute("listCategory", listCategory);

        String newPage = "book_form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
        requestDispatcher.forward(request, response);
    }

    public void createBook() throws ServletException, IOException {
        String title = request.getParameter("title");

        Book existBook = bookDAO.findByTitle(title);
        if(existBook !=null){
            String message = "Could not create new book because the title '" + title + "' already exists.";
            listBooks(message);
            return;
        }

        Book newBook = new Book();
        readBookFields(newBook);

        Book createdBook = bookDAO.create(newBook);

        if (createdBook.getBookId() > 0) {
            String message = "A new book has been created successfully.";
            listBooks(message);
        }
    }

    public void readBookFields(Book book) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String isbn = request.getParameter("isbn");
        float price = Float.parseFloat(request.getParameter("price"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date publishDate = null;

        try {
            publishDate = dateFormat.parse(request.getParameter("publishDate"));
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new ServletException("Error parsing publish date (format is yyyy-MM-dd)");
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setPublishDate(publishDate);

        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryDAO.get(categoryId);
        book.setCategory(category);

        book.setPrice(price);

        Part part = request.getPart("bookImage");

        if (part != null && part.getSize() > 0) {
            long size = part.getSize();
            byte[] imageBytes = new byte[(int) size];

            InputStream inputStream = part.getInputStream();
            inputStream.read(imageBytes);
            inputStream.close();

            book.setImage(imageBytes);
        }
    }

}
