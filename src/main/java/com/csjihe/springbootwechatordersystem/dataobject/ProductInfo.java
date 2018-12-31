package com.csjihe.springbootwechatordersystem.dataobject;

/**
 * Product DAO
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    /** Name */
    private String productName;

    /** Unit Price */
    private BigDecimal productPrice;

    /** Stock */
    private Integer ProductStock;

    /** Description */
    private String productDescription;

    /** Icon */
    private String productIcon;

    /*** Status, 0 normal, 1 out of stack */
    private Integer productStatus;

    /** Category Number */
    private Integer categoryType;
}
