package com.e_commerce.service;

import com.e_commerce.model.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> addProduct(ProductDto productdto);

    ResponseEntity<?> getProductById(Long id);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> updateProduct(ProductDto productdto, Long id);

    void deleteProduct(Long id);
}
