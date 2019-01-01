package com.csjihe.springbootwechatordersystem.service.implementation;

import com.csjihe.springbootwechatordersystem.dto.OrderDTO;
import com.csjihe.springbootwechatordersystem.enums.ResultEnum;
import com.csjihe.springbootwechatordersystem.exception.SellException;
import com.csjihe.springbootwechatordersystem.service.BuyerService;
import com.csjihe.springbootwechatordersystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);

        if (orderDTO == null) {
            log.error("【取消订单】No Such Order, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);

    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        if (orderDTO == null) {
            return null;
        }

        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】 Not the same openid. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNERSHIP_ERROR);
        }
        return orderDTO;
    }
}
