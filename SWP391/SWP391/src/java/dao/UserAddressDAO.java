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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;

public class UserAddressDAO extends DBContext {

    // Create method
    public void addUserAddress(UserAddress userAddress) {
        String sql = "INSERT INTO user_address (user_id, address_line, city, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userAddress.getUserId());
            statement.setString(2, userAddress.getAddressLine());
            statement.setString(3, userAddress.getCity());
            statement.setString(4, userAddress.getCountry());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user address was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read method
    public List<UserAddress> getAllUserAddresses() {
        List<UserAddress> userAddresses = new ArrayList<>();
        String sql = "SELECT id, user_id, address_line, city, country FROM user_address";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserAddress userAddress = new UserAddress();
                userAddress.setId(resultSet.getInt("id"));
                userAddress.setUserId(resultSet.getInt("user_id"));
                userAddress.setAddressLine(resultSet.getString("address_line"));
                userAddress.setCity(resultSet.getString("city"));
                userAddress.setCountry(resultSet.getString("country"));
                userAddresses.add(userAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAddresses;
    }

    public UserAddress getUserAddressById(int addressId) {
        UserAddress userAddress = null;
        String sql = "SELECT id, user_id, address_line, city, country FROM user_address WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, addressId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userAddress = new UserAddress();
                    userAddress.setId(resultSet.getInt("id"));
                    userAddress.setUserId(resultSet.getInt("user_id"));
                    userAddress.setAddressLine(resultSet.getString("address_line"));
                    userAddress.setCity(resultSet.getString("city"));
                    userAddress.setCountry(resultSet.getString("country"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAddress;
    }
    public static void main(String[] args) {
        UserAddressDAO uaDAO = new UserAddressDAO();
        System.out.println(uaDAO.getUserAddressById(1));
    }
}
