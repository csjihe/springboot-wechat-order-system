package com.csjihe.springbootwechatordersystem.enums;


import lombok.Getter;

/**
 * Product Status
 */

@Getter
public enum ProductStatusEnum {


    UP(0, "在架"),
    DOWN(0, "已下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
