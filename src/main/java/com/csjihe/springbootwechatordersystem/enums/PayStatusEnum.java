package com.csjihe.springbootwechatordersystem.enums;


import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT(0, "Waiting for payment"),
    SUCCESS(1, "Payment success")
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
