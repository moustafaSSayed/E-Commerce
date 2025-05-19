package com.e_commerce.service.impl;

import com.e_commerce.model.Cart;
import com.e_commerce.model.Product;
import com.e_commerce.model.User;
import com.e_commerce.repository.CartRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<String> addToCart(Long productId) {
        User user = getCurrentUser();
        if (user.getCart() == null) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            user.setCart(cart);
        }
        Cart cart = user.getCart();
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            log.info("Product with ID: {} Not Found in The Cart With ID: {}", productId, cart.getId());
            return ResponseEntity.badRequest().body("Product not found");
        }
        cart.getProducts().add(product);
        cartRepository.save(cart);
        log.info("Product With ID: {} Added Successfully to the cart with ID: {}", productId, cart.getId());
        return ResponseEntity.ok("Product Added Successfully to the cart");
    }

    @Override
    public ResponseEntity<String> removeFromCart(Long productId) {
        User user = getCurrentUser();
        Cart cart = user.getCart();
        if (cart == null) {
            log.info("Cart of user with ID: {} not found", user.getId());
            return ResponseEntity.badRequest().body("Cart not found");
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            log.info("Product with ID: {} Not Found in The Cart With ID: {}", productId, cart.getId());
            return ResponseEntity.badRequest().body("Product not found");
        }
        cart.getProducts().removeIf(p -> p.getId().equals(productId));
        cartRepository.save(cart);
        log.info("Product with ID: {} Removed Successfully from the cart with ID: {}", productId, cart.getId());
        return ResponseEntity.ok("Product Removed Successfully from the cart");
    }

    @Override
    public ResponseEntity<?> getCartItems() {
        User user = getCurrentUser();
        Cart cart = user.getCart();
        if (cart == null) {
            log.info("Cart of user with ID: {} not found", user.getId());
            return ResponseEntity.badRequest().body("Cart not found");
        }
        log.info("Cart of user with ID: {} Found Successfully", user.getId());
        return ResponseEntity.ok(cart.getProducts());
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
