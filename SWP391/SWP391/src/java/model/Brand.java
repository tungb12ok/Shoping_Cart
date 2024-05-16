package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Brand {
    private int id;
    private int statusId;
    private String name;
    private Date createdAt;
    private Date modifiedAt;

    private BrandStatus brandStatus;
}