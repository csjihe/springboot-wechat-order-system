package com.csjihe.springbootwechatordersystem.service;

import com.csjihe.springbootwechatordersystem.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);


    /** Find all on-shelf product */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // Increase stock


    // Decrease stock
}
