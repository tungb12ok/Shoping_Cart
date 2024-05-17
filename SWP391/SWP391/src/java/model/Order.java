/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Data;

@Data
public class Order {
    private int id;
    private int userId;
    private int statusId;
    private String email;
    private String address;
    private double orderTotal;
    private String recipient;
    private String recipientPhone;
    private Date createdAt;
    private Date modifiedAt;

    private User user;
    private OrderStatus orderStatus;
}
