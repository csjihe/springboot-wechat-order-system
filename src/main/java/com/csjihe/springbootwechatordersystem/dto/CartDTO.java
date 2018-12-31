package com.csjihe.springbootwechatordersystem.dto;


import lombok.Data;

/**
 * Shopping cart
 */
@Data
public class CartDTO {

    /** Product Id */
    private String productId;


    /** Product Quantity */
    private Integer productQuantity;


    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
