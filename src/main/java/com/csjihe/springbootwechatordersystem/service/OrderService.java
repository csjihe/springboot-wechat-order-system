package com.csjihe.springbootwechatordersystem.service;

import com.csjihe.springbootwechatordersystem.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /** Set up an order */
    OrderDTO create(OrderDTO orderDTO);

    /** Query a single order */
    OrderDTO findOne(String orderId);

    /** Query a list of orders */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** Cancel an order */
    OrderDTO cancel(OrderDTO orderDTO);

    /** Finish an order */
    OrderDTO finish(OrderDTO orderDTO);

    /** Pay an order */
    OrderDTO paid(OrderDTO orderDTO);
}
