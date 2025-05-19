package com.e_commerce.service;

import com.e_commerce.model.Category;
import com.e_commerce.model.dto.CategoryDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CategoryService {

    ResponseEntity<?> addCategory(CategoryDto categoryDto);

    ResponseEntity<?> getCategoryById(Long id);

    Optional<Category> getCategoryByName(String name);

    ResponseEntity<?> getAllCategories();

    ResponseEntity<?> updateCategory(CategoryDto categoryDto, Long id);

    void deleteCategory(Long id);
}
