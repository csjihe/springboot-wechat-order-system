package com.csjihe.springbootwechatordersystem.enums;


import lombok.Getter;

/**
 * Enum for order status
 */

@Getter
public enum OrderStatusEnum {
    NEW(0, "New Order"),
    FINISHED(1, "Order Finished"),
    CANCELLED(2, "Order Cancelled"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
