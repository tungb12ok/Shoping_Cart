package model;

import lombok.Data;

import java.sql.Date;

@Data
public class ShopingSession {
    private int id;
    private int userId;
    private int total;
    private Date createAt;
    private Date modifiedAt;

    private User user;
}
