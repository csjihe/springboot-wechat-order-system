package com.csjihe.springbootwechatordersystem.service.implementation;

import com.csjihe.springbootwechatordersystem.dataobject.ProductInfo;
import com.csjihe.springbootwechatordersystem.enums.ProductStatusEnum;
import com.csjihe.springbootwechatordersystem.repository.ProductInfoRepository;
import com.csjihe.springbootwechatordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
