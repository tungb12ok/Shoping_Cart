/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Blog;
import model.Pagination;
import model.User;

/**
 *
 * @author tungl
 */
@WebServlet(name = "CustomerListController", urlPatterns = {"/customerList"})
public class CustomerListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          UserDAO uDAO = new UserDAO();
        List<User> list = uDAO.getAllUsers();
        HttpSession session = request.getSession();
         // start pagging
        int limitPage = 1;
        if (request.getParameter("cp") == null) {
            Pagination Page = new Pagination(list, limitPage, 1);
            Pagination<User> pagination = new Pagination<>(list, limitPage, 1);
            list = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("list", pagination.getItemsOnPage());
        } else if (request.getParameter("cp") != null) {
            int cp = Integer.parseInt(request.getParameter("cp"));
            Pagination Page = new Pagination(list, limitPage, cp);
            Pagination<User> pagination = new Pagination<>(list, limitPage, cp);
            list = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
        }
        // set URL
        request.setAttribute("pagging", "customerList");
        // end pagging
        request.setAttribute("listUser", list);
        request.getRequestDispatcher("viewsAdmin/viewCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
