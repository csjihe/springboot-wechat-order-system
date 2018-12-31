package com.csjihe.springbootwechatordersystem.controller;


import com.csjihe.springbootwechatordersystem.VO.ProductInfoVO;
import com.csjihe.springbootwechatordersystem.VO.ProductVO;
import com.csjihe.springbootwechatordersystem.VO.ResultVO;
import com.csjihe.springbootwechatordersystem.dataobject.ProductCategory;
import com.csjihe.springbootwechatordersystem.dataobject.ProductInfo;
import com.csjihe.springbootwechatordersystem.service.CategoryService;
import com.csjihe.springbootwechatordersystem.service.ProductService;
import com.csjihe.springbootwechatordersystem.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {

        /**
         *
         *
         *
         */
        // 1. Search for all products that hit the shelves
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2. Search category(一次性查询)
        // List<Integer> categoryTypeList = new ArrayList<>();

        // traditional way
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        // Java 8, Stream,  Lambda
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList
                = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory  productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());


            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }



        return ResultVOUtil.success(productVOList);
    }

}
