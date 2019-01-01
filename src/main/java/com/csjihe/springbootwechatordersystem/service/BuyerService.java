package com.csjihe.springbootwechatordersystem.service;

import com.csjihe.springbootwechatordersystem.dto.OrderDTO;

/**
 * Buyer
 */

public interface BuyerService {
    // Serach for one order
    OrderDTO findOrderOne(String openid, String orderId);


    // Cancel one order
    OrderDTO cancelOrderOne(String openid, String orderId);
}
