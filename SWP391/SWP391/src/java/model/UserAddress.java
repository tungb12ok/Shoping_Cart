/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tungl
 */
public class UserAddress {
    private int id;
    private int userId;
    private String addressLine;
    private String city;
    private String country;

    public UserAddress() {
    }

    public UserAddress(int id, int userId, String addressLine, String city, String country) {
        this.id = id;
        this.userId = userId;
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UserAddress{" + "id=" + id + ", userId=" + userId + ", addressLine=" + addressLine + ", city=" + city + ", country=" + country + '}';
    }
    
    
}
