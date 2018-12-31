package com.csjihe.springbootwechatordersystem.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** Order Id */
    private String orderId;

    /** Product Id */
    private String productId;

    /** Product Name */
    private String productName;

    /** Unit Price */
    private BigDecimal productPrice;

    /** Product Quantity */
    private Integer productQuantity;

    /** Product Icon */
    private String productIcon;

}
