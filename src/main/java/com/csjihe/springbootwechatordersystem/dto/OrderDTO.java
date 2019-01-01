package com.csjihe.springbootwechatordersystem.dto;


import com.csjihe.springbootwechatordersystem.dataobject.OrderDetail;
import com.csjihe.springbootwechatordersystem.enums.OrderStatusEnum;
import com.csjihe.springbootwechatordersystem.enums.PayStatusEnum;
import com.csjihe.springbootwechatordersystem.utils.serializer.DataToLongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    /** Order ID */
    private String orderId;

    /** Buyer Name */
    private String buyerName;

    /** Buyer Phone */
    private String buyerPhone;

    /** Buyer Address */
    private String buyerAddress;

    /** Buyer Wechat OpenId */
    private String buyerOpenid;

    /**  Buyer Order Amount */
    private BigDecimal orderAmount;

    /** Order status, default as new order */
    private Integer orderStatus;

    /** Payment status, default as unpaid denoted as 0 */
    private Integer payStatus;

    /**  Order created time */
    /** Add @DynamicUpdate annotation */
    @JsonSerialize(using = DataToLongSerializer.class)
    private Date createTime;


    /** Order updated time */
    /** Add @DynamicUpdate annotation */
    @JsonSerialize(using = DataToLongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
