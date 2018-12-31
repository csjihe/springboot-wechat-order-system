package com.csjihe.springbootwechatordersystem.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "Product Not Exist"),

    PRODUCT_STOCK_ERROR(11, "Stock Error"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
