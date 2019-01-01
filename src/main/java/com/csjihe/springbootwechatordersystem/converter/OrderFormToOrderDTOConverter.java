package com.csjihe.springbootwechatordersystem.converter;


import com.csjihe.springbootwechatordersystem.dataobject.OrderDetail;
import com.csjihe.springbootwechatordersystem.dto.OrderDTO;
import com.csjihe.springbootwechatordersystem.enums.ResultEnum;
import com.csjihe.springbootwechatordersystem.exception.SellException;
import com.csjihe.springbootwechatordersystem.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormToOrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        /**
         * Cannot use BeanUtils to convert properties,
         * The names of properties in OrderDTO and OrderForm are different
         */
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        } catch (Exception e){
            log.error("【对象转换】Error, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
