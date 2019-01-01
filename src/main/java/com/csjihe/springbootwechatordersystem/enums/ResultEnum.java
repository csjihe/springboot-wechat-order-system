package com.csjihe.springbootwechatordersystem.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "Parameter error"),

    PRODUCT_NOT_EXIST(10, "Product Not Exist"),

    PRODUCT_STOCK_ERROR(11, "Stock Error"),

    ORDER_NOT_EXIST(12, "Order Not Exist"),

    ORDERDETAIL_NOT_EXIST(13, "Order Is Empty"),

    ORDER_STATUS_ERROR(14, "Order Status Error"),

    ORDER_UPDATE_ERROR (15, "Failed to Update Order"),

    ORDER_DETAIL_EMPTY(16, "No Order Detail"),

    ORDER_PAY_STATUS_ERROR(17, "Order Payment Status Error"),

    CART_EMPTY(18, "Cart is empty"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
