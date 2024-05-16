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
import java.util.Date;
import model.User;

/**
 *
 * @author tungl
 */
@WebServlet(name = "UpdateCustomerController", urlPatterns = {"/updateCustomer"})
public class UpdateCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String idRaw = request.getParameter("id");

        UserDAO uDAO = new UserDAO();
        request.setAttribute("listRole", uDAO.getAllRoles());
        request.setAttribute("listStatus", uDAO.getAllStatus());

        if (idRaw == null) {
            request.getRequestDispatcher("viewsAdmin/updateCustomer.jsp").forward(request, response);

        } else {
            User u = uDAO.getUserById(Integer.parseInt(idRaw));

            if (u == null) {
                response.sendRedirect("Error.jsp");
            } else {
                request.setAttribute("user", u);
                request.getRequestDispatcher("viewsAdmin/updateCustomer.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String idRaw = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        int roleId = Integer.parseInt(request.getParameter("role"));
        int satusId = Integer.parseInt(request.getParameter("status"));

        UserDAO uDAO = new UserDAO();
        // list role
        request.setAttribute("listRole", uDAO.getAllRoles());
        request.setAttribute("listStatus", uDAO.getAllStatus());

        // Create a User object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setGender(gender);
        user.setRoleId(roleId);
        user.setStatusId(satusId);
        user.setCreatedAt(new Date());
        user.setModifiedAt(new Date());

        if (idRaw.isBlank() || idRaw.isEmpty() || idRaw == null) {
            try {
                uDAO.addUser(user);
                request.setAttribute("mess", "Create User Success!");
                response.sendRedirect("customerList");
                return;
            } catch (Exception e) {
                request.setAttribute("mess", "Create User Failed!");
            }
        } else {
            User uUpdate = uDAO.getUserById(Integer.parseInt(idRaw));
            uUpdate.setFirstName(firstName);
            uUpdate.setLastName(lastName);
            uUpdate.setEmail(email);
            uUpdate.setPassword(password);
            uUpdate.setTelephone(telephone);
            uUpdate.setGender(gender);
            uUpdate.setRoleId(roleId);
            uUpdate.setCreatedAt(new Date());
            uUpdate.setModifiedAt(new Date());

            try {
                uDAO.updateUser(user);
                request.setAttribute("mess", "Update User Success!");
            } catch (Exception e) {
                request.setAttribute("mess", "Update User Failed!");
            }
        }
        response.sendRedirect("updateCustomer?id" + idRaw);

    }
}
