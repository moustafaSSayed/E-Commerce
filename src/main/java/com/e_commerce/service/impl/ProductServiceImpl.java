package com.e_commerce.service.impl;

import com.e_commerce.mapper.ProductMapper;
import com.e_commerce.model.Category;
import com.e_commerce.model.Product;
import com.e_commerce.model.dto.ProductDto;
import com.e_commerce.repository.CategoryRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addProduct(@Valid ProductDto productdto) {
        Optional<Product> product = productRepository.findByName(productdto.getProductName());
        if (product.isEmpty()) {
            productRepository.save(productMapper.mapToProduct(productdto));
            log.info("Product with name: {} Added Successfully", productdto.getProductName());
            return ResponseEntity.ok("Product Added Successfully");
        }
        log.info("Product with name: {} Already Exists", productdto.getProductName());
        return ResponseEntity.badRequest().body("Product Already Exists");
    }

    @Override
    public ResponseEntity<?> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDto productDto = productMapper.mapToProductDto(product.get());
            log.info("Product with id: {} Found Successfully", id);
            return ResponseEntity.ok(productDto);
        }
        log.info("Product with id: {} Not Found", id);
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        log.info("Fetching all products");
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productRepository.findAll()));
    }

    @Override
    public ResponseEntity<?> updateProduct(@Valid ProductDto productdto, Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setName(productdto.getProductName());
            product.get().setProductPrice(productdto.getProductPrice());
            Optional<Category> category = categoryRepository.findByName(productdto.getCategoryName());
            if (category.isPresent()) {
                product.get().setProductCategory(category.get());
            } else
                return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
            log.info("Product with id: {} updated", id);
            productRepository.save(product.get());
            return ResponseEntity.ok("Product updated successfully");
        }
        log.info("Product with id: {} not found", id);
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Product with id: {} deleted Successfully", id);
        productRepository.deleteById(id);
    }
}
