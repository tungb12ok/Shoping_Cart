/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.dashboard.*;
import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author tungl
 */
@WebServlet(name = "BlogController", urlPatterns = {"/blogs"})
public class BlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogDAO bDAO = new BlogDAO();
        HttpSession session = request.getSession();
        String search = request.getParameter("search");

        request.setAttribute("listBlog", bDAO.getAllBlog(null));
        session.setAttribute("listBlogCategoriess", bDAO.getBlogsByCategory());
        session.setAttribute("listLastBlog", bDAO.getAllBlog(3));
        if (search != null) {
            request.setAttribute("listBlog", bDAO.filterBlog(search, null));
        }
        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
