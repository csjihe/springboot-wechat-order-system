package com.csjihe.springbootwechatordersystem.dataobject;


import com.csjihe.springbootwechatordersystem.enums.OrderStatusEnum;
import com.csjihe.springbootwechatordersystem.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /** Order ID */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** Payment status, default as unpaid denoted as 0 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**  Order created time */
    /** Add @DynamicUpdate annotation */
    private Date createTime;


    /** Order updated time */
    /** Add @DynamicUpdate annotation */
    private Date updateTime;
}
