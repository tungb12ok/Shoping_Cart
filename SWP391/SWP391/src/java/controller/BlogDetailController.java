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
import model.Blog;

/**
 *
 * @author tungl
 */
@WebServlet(name = "BlogDetailController", urlPatterns = {"/blogDetail"})
public class BlogDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");

        BlogDAO bDAO = new BlogDAO();

        try {
            int id = Integer.parseInt(idRaw);
            Blog b = bDAO.getByID(id);
            if (b == null) {
                response.sendRedirect("Error.jsp");
            }
            request.setAttribute("blog", b);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
        request.getRequestDispatcher("blog_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
