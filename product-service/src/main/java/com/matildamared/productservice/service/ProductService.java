package com.matildamared.productservice.service;

import com.matildamared.productservice.dto.ProductRequest;
import com.matildamared.productservice.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }
}
