/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class CategoryDAO extends DBContext {

    // Method to retrieve a Category by its ID
    public Category getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Category category = null;

        try {
            // SQL query to select a category by its ID
            String query = "SELECT id, status_id, name, created_at, modified_at FROM categories WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a Category object
            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setStatusId(resultSet.getInt("status_id"));
                category.setName(resultSet.getString("name"));
                category.setCreateAt(resultSet.getDate("created_at"));
                category.setModifiedAt(resultSet.getDate("modified_at"));
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return category;
    }

    // Method to retrieve a Category ID by its name
    public int getCategoryId(String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int categoryId = -1;

        try {
            // SQL query to select a category ID by its name
            String query = "SELECT id FROM categories WHERE name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, retrieve the category ID
            if (resultSet.next()) {
                categoryId = resultSet.getInt("id");
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return categoryId;
    }

    // Method to retrieve all categories
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all categories
            String query = "SELECT id, status_id, name, created_at, modified_at FROM categories";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with Category objects
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setStatusId(resultSet.getInt("status_id"));
                category.setName(resultSet.getString("name"));
                category.setCreateAt(resultSet.getDate("created_at"));
                category.setModifiedAt(resultSet.getDate("modified_at"));
                categories.add(category);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return categories;
    }

    // Method to add a new category
    public void add(Category category) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to insert a new category
            String query = "INSERT INTO categories (status_id, name, created_at, modified_at) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category.getStatusId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setDate(3, (Date) category.getCreateAt());
            preparedStatement.setDate(4, (Date) category.getModifiedAt());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to update an existing category
    public void update(Category category) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to update an existing category
            String query = "UPDATE categories SET status_id=?, name=?, modified_at=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category.getStatusId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setDate(3, (Date) category.getModifiedAt());
            preparedStatement.setInt(4, category.getId());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to delete a category
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to delete a category by its ID
            String query = "DELETE FROM categories WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    public CategoryStatus getStatusById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CategoryStatus categoryStatus = null;

        try {
            // SQL query to select a category status by its ID
            String query = "SELECT id, name FROM category_status WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a CategoryStatus object
            if (resultSet.next()) {
                categoryStatus = new CategoryStatus();
                categoryStatus.setId(resultSet.getInt("id"));
                categoryStatus.setName(resultSet.getString("name"));
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return categoryStatus;
    }

    // Method to retrieve all CategoryStatus entries
    public List<CategoryStatus> getAllCategory() throws SQLException {
        List<CategoryStatus> statuses = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all category statuses
            String query = "SELECT id, name FROM category_status";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with CategoryStatus objects
            while (resultSet.next()) {
                CategoryStatus status = new CategoryStatus();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return statuses;
    }

    // Method to add a new category status
    public void add(CategoryStatus categoryStatus) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to insert a new category status
            String query = "INSERT INTO category_status (name) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryStatus.getName());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to update an existing category status
    public void update(CategoryStatus categoryStatus) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to update an existing category status
            String query = "UPDATE category_status SET name=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryStatus.getName());
            preparedStatement.setInt(2, categoryStatus.getId());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to delete a category status
    public void deleteCategory(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to delete a category status by its ID
            String query = "DELETE FROM category_status WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public static void main(String[] args) {
        CategoryDAO cDAO = new CategoryDAO();
        try {
            System.out.println(cDAO.getAllCategory());
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
