package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Product {
    private int id;
    private int categoryId;
    private int discountId;
    private int statusId;
    private int brandId;
    private String name;
    private int quantity;
    private float price;
    private String size;
    private String color;
    private String description;
    private String img1;
    private String img2;
    private Date createdAt;
    private Date modifiedAt;

    private Brand brand;
    private Discount discount;
    private ProductStatus productStatus;
    private Categories categories;
}
