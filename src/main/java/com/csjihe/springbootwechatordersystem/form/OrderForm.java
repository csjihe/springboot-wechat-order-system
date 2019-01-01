package com.csjihe.springbootwechatordersystem.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {


    /**
     * Customer's name
     */
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    /**
     * Customer's cell phone number
     */
    @NotEmpty(message = "Cellphone number cannot be empty")
    private String phone;

    /**
     * Customer's address
     */
    @NotEmpty(message = "Address cannot be empty")
    private String address;

    /**
     * Customer's Wechat openid
     */
    @NotEmpty(message = "Wechat Openid cannot be empty")
    private String openid;


    /**
     * Shopping cart information
     */
    @NotEmpty(message = "Cart cannot be empty")
    private String items;
}
