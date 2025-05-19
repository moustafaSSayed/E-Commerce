package com.e_commerce.controller;

import com.e_commerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/get-cart")
    public ResponseEntity<?> getCartItems(){
        return cartService.getCartItems();
    }

    @PostMapping("/add-item/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Long productId){
        return cartService.addToCart(productId);
    }

    @DeleteMapping("/remove-item/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId){
        return cartService.removeFromCart(productId);
    }


}
