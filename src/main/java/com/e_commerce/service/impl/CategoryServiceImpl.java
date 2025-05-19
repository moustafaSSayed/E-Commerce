package com.e_commerce.service.impl;

import com.e_commerce.mapper.CategoryMapper;
import com.e_commerce.model.Category;
import com.e_commerce.model.dto.CategoryDto;
import com.e_commerce.repository.CategoryRepository;
import com.e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addCategory(CategoryDto categoryDto) {
        if (getCategoryByName(categoryDto.getCategoryName()).isEmpty()) {
            Category category = CategoryMapper.mapToCategory(categoryDto);
            categoryRepository.save(category);
            log.info("Category with name: {} added successfully", category.getName());
            return ResponseEntity.ok("Category Added Successfully");
        }
        log.info("Category with name: {} already exists", categoryDto.getCategoryName());
        return new ResponseEntity<>("Category Already Exists", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            log.info("Category with id: {} found", id);
            return ResponseEntity.ok(CategoryMapper.mapToCategoryDto(category.get()));
        }
        log.info("Category with id: {} not found", id);
        return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        log.info("Finding category with name: {}", name);
        return categoryRepository.findByName(name);
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        log.info("Fetching all categories");
        return ResponseEntity
                .ok(CategoryMapper.mapToCategoryDtoList(
                        categoryRepository.findAll()));
    }

    @Override
    public ResponseEntity<?> updateCategory(CategoryDto categoryDto, Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setName(categoryDto.getCategoryName());
            categoryRepository.save(category.get());
            log.info("Category with id: {} updated", id);
            return ResponseEntity.ok("Category Updated Successfully");
        }
        log.info("Category with id: {} not found", id);
        return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category with id: {}", id);
        categoryRepository.deleteById(id);
    }
}
