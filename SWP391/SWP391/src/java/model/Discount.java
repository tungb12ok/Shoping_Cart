package model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author tungl
 */
@Data
public class Discount {
    private int id;
    private String name;
    private String description;
    private int discountPercent;
    private boolean active;
    private Date createdAt;
    private Date modifiedAt;
}
