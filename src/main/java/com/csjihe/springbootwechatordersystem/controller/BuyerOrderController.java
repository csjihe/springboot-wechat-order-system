package com.csjihe.springbootwechatordersystem.controller;


import com.csjihe.springbootwechatordersystem.VO.ResultVO;
import com.csjihe.springbootwechatordersystem.converter.OrderFormToOrderDTOConverter;
import com.csjihe.springbootwechatordersystem.dto.OrderDTO;
import com.csjihe.springbootwechatordersystem.enums.ResultEnum;
import com.csjihe.springbootwechatordersystem.exception.SellException;
import com.csjihe.springbootwechatordersystem.form.OrderForm;
import com.csjihe.springbootwechatordersystem.service.OrderService;
import com.csjihe.springbootwechatordersystem.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    // Create Order
    // Map<String, String> is map
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】Parameter error, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());
        }

        // Convert OrderForm to Order DTO.
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】Shopping Cart cannot be empty");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();

        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    // List Order
    // Use page
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid is empty");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);


        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    // Order Detail
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO
        //Possible security issue
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);

    }

    // Cancel Order
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        //TODO Security Issue
        orderService.cancel(orderDTO);

        return ResultVOUtil.success();
    }

}
