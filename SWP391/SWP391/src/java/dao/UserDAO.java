/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;

/**
 *
 * @author tungl
 */
public class UserDAO extends DBContext {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, email, password, role_id, status_id, first_name, last_name, telephone, created_at, modified_at, gender FROM users";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setStatusId(resultSet.getInt("status_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setCreatedAt(resultSet.getDate("created_at"));
                user.setGender(resultSet.getBoolean("gender"));
                user.setModifiedAt(resultSet.getDate("modified_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể throw hoặc log
        }
        return users;
    }

    public List<User> filterCustomersByStatusAndSearch(Integer status, String searchTerm) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, email, password, role_id, status_id, first_name, last_name, telephone, created_at, modified_at, gender FROM users WHERE (status_id = ? OR ? IS NULL) AND ((first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR telephone LIKE ?) OR ? IS NULL)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (status != null) {
                statement.setInt(1, status);
                statement.setInt(6, status);
            } else {
                statement.setNull(1, java.sql.Types.INTEGER);
                statement.setNull(6, java.sql.Types.INTEGER);
            }
            if (searchTerm != null) {
                statement.setString(3, "%" + searchTerm + "%");
                statement.setString(4, "%" + searchTerm + "%");
                statement.setString(5, "%" + searchTerm + "%");
                statement.setString(6, "%" + searchTerm + "%");
            } else {
                statement.setNull(3, java.sql.Types.VARCHAR);
                statement.setNull(4, java.sql.Types.VARCHAR);
                statement.setNull(5, java.sql.Types.VARCHAR);
                statement.setNull(6, java.sql.Types.VARCHAR);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRoleId(resultSet.getInt("role_id"));
                    user.setStatusId(resultSet.getInt("status_id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setTelephone(resultSet.getString("telephone"));
                    user.setGender(resultSet.getBoolean("gender"));
                    user.setCreatedAt(resultSet.getDate("created_at"));
                    user.setModifiedAt(resultSet.getDate("modified_at"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể throw hoặc log
        }
        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT id, email, password, role_id, status_id, first_name, last_name, telephone, created_at, modified_at, gender FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRoleId(resultSet.getInt("role_id"));
                    user.setStatusId(resultSet.getInt("status_id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setTelephone(resultSet.getString("telephone"));
                    user.setGender(resultSet.getBoolean("gender"));
                    user.setCreatedAt(resultSet.getDate("created_at"));
                    user.setModifiedAt(resultSet.getDate("modified_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể throw hoặc log
        }
        return user;
    }

    public static void main(String[] args) {
        UserDAO uDAO = new UserDAO();
        System.out.println(uDAO.getAllUsers());
    }
}
