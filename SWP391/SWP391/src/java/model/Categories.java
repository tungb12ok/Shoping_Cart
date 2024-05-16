package model;

import lombok.Data;

import java.util.Date;

@Data

public class Categories {
    private int id;
    private int statusId;
    private String name;
    private Date createAt;
    private Date modifiedAt;

    private CategoryStatus categoryStatus;
}
