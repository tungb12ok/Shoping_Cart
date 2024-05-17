/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ProductDAO extends DBContext {

    // Method to retrieve all products
    public List<Product> getAll(ProductStatus status) throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT id, category_id, discount_id, status_id, brand_id, ");
            queryBuilder.append("name, quantity, price, size, color, description, img1, img2, created_at, modified_at ");
            queryBuilder.append("FROM products");

            if (status != null) {
                queryBuilder.append(" WHERE status_id = ?");
            }

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            if (status != null) {
                preparedStatement.setInt(1, status.getId());
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setDiscountId(resultSet.getInt("discount_id"));
                product.setStatusId(resultSet.getInt("status_id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice((float) resultSet.getDouble("price"));
                product.setSize(resultSet.getString("size"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setImg1(resultSet.getString("img1"));
                product.setImg2(resultSet.getString("img2"));
                product.setCreatedAt(resultSet.getDate("created_at"));
                product.setModifiedAt(resultSet.getDate("modified_at"));
                products.add(product);
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

        return products;
    }

    // Method to retrieve products with optional filters
    public List<Product> getFilteredProducts(Double minPrice, Double maxPrice, String searchKeyword, Integer categoryId, String size, String color, ProductStatus status) throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT id, category_id, discount_id, status_id, brand_id, ");
            queryBuilder.append("name, quantity, price, size, color, description, img1, img2, created_at, modified_at ");
            queryBuilder.append("FROM products WHERE 1=1");

            if (minPrice != null) {
                queryBuilder.append(" AND price >= ?");
            }

            if (maxPrice != null) {
                queryBuilder.append(" AND price <= ?");
            }

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                queryBuilder.append(" AND (name LIKE ? OR description LIKE ?)");
            }

            if (categoryId != null) {
                queryBuilder.append(" AND category_id = ?");
            }

            if (size != null && !size.isEmpty()) {
                queryBuilder.append(" AND size = ?");
            }

            if (color != null && !color.isEmpty()) {
                queryBuilder.append(" AND color = ?");
            }

            if (status != null) {
                queryBuilder.append(" AND status_id = ?");
            }

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            int parameterIndex = 1;

            if (minPrice != null) {
                preparedStatement.setDouble(parameterIndex++, minPrice);
            }

            if (maxPrice != null) {
                preparedStatement.setDouble(parameterIndex++, maxPrice);
            }

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                preparedStatement.setString(parameterIndex++, "%" + searchKeyword + "%");
                preparedStatement.setString(parameterIndex++, "%" + searchKeyword + "%");
            }

            if (categoryId != null) {
                preparedStatement.setInt(parameterIndex++, categoryId);
            }

            if (size != null && !size.isEmpty()) {
                preparedStatement.setString(parameterIndex++, size);
            }

            if (color != null && !color.isEmpty()) {
                preparedStatement.setString(parameterIndex++, color);
            }

            if (status != null) {
                preparedStatement.setInt(parameterIndex, status.getId());
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setDiscountId(resultSet.getInt("discount_id"));
                product.setStatusId(resultSet.getInt("status_id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice((float) resultSet.getDouble("price"));
                product.setSize(resultSet.getString("size"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setImg1(resultSet.getString("img1"));
                product.setImg2(resultSet.getString("img2"));
                product.setCreatedAt(resultSet.getDate("created_at"));
                product.setModifiedAt(resultSet.getDate("modified_at"));
                products.add(product);
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

        return products;
    }

    // Method to retrieve a Product by its ID
    public Product getProductById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            // SQL query to select a product by its ID
            String query = "SELECT id, category_id, discount_id, status_id, brand_id, "
                    + "name, quantity, price, size, color, description, img1, img2, created_at, modified_at "
                    + "FROM products WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a Product object
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setDiscountId(resultSet.getInt("discount_id"));
                product.setStatusId(resultSet.getInt("status_id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice((float) resultSet.getDouble("price"));
                product.setSize(resultSet.getString("size"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setImg1(resultSet.getString("img1"));
                product.setImg2(resultSet.getString("img2"));
                product.setCreatedAt(resultSet.getDate("created_at"));
                product.setModifiedAt(resultSet.getDate("modified_at"));
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

        return product;
    }
}
