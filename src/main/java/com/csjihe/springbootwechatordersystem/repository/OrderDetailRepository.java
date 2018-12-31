package com.csjihe.springbootwechatordersystem.repository;

import com.csjihe.springbootwechatordersystem.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

    List<OrderDetail> findByOrderId(String OrderId);
}
