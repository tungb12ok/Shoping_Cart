package model;

import lombok.Data;

import java.sql.Date;

@Data
public class CartItem {
    private int id;
    private int sessionId;
    private int productId;
    private int quantity;
    private Date createAt;
    private Date modifiedAt;

    private ShopingSession shopingSession;
}
