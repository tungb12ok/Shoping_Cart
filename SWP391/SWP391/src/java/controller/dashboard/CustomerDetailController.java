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
import model.User;

/**
 *
 * @author tungl
 */
@WebServlet(name = "CustomerDetailController", urlPatterns = {"/customerDetail"})
public class CustomerDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO uDAO = new UserDAO();
        
         String idRaw = request.getParameter("id");

        try {
            int id = Integer.parseInt(idRaw);
            User b = uDAO.getUserById(id);
            if (b == null) {
                response.sendRedirect("Error.jsp");
            }
            request.setAttribute("u", b);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
        request.getRequestDispatcher("viewsAdmin/customerDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
