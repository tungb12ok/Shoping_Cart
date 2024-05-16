package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Ratting {
    private int id;
    private int productId;
    private int userId;
    private int ratting;
    private String comment;
    private Date createAt;
    private Date modifiedAt;

    private User user;
    private Product product;
}
