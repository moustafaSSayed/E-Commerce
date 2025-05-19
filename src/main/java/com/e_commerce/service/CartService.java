package com.e_commerce.service;

import com.e_commerce.model.User;
import org.springframework.http.ResponseEntity;

public interface CartService {

    public ResponseEntity<String> addToCart(Long productId);

    public ResponseEntity<String> removeFromCart(Long productId);

    public ResponseEntity<?> getCartItems();
}
