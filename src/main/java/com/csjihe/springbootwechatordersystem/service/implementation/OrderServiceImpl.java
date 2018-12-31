package com.csjihe.springbootwechatordersystem.service.implementation;

import com.csjihe.springbootwechatordersystem.dataobject.OrderDetail;
import com.csjihe.springbootwechatordersystem.dataobject.OrderMaster;
import com.csjihe.springbootwechatordersystem.dataobject.ProductInfo;
import com.csjihe.springbootwechatordersystem.dto.CartDTO;
import com.csjihe.springbootwechatordersystem.dto.OrderDTO;
import com.csjihe.springbootwechatordersystem.enums.OrderStatusEnum;
import com.csjihe.springbootwechatordersystem.enums.PayStatusEnum;
import com.csjihe.springbootwechatordersystem.enums.ResultEnum;
import com.csjihe.springbootwechatordersystem.exception.SellException;
import com.csjihe.springbootwechatordersystem.repository.OrderDetailRepository;
import com.csjihe.springbootwechatordersystem.repository.OrderMasterRepository;
import com.csjihe.springbootwechatordersystem.service.OrderService;
import com.csjihe.springbootwechatordersystem.service.ProductService;
import com.csjihe.springbootwechatordersystem.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


//    List<CartDTO> cartDTOS = new ArrayList<>();

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 1. 查询商品 数量 价格 库存 等
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2. 计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
            .add(orderAmount);

            //  订单详情入库 OrderDetail
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            // copy other Product properties from productInfo to orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetailRepository.save(orderDetail);

        }


        // 3. 写入订单数据库 OrderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        orderMasterRepository.save(orderMaster);

        // 4. Update 库存
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(e ->
            new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());


        productService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String OrderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
