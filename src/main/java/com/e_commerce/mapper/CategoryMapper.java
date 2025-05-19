package com.e_commerce.mapper;

import com.e_commerce.model.Category;
import com.e_commerce.model.dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public static CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(category.getName());
        return categoryDto;
    }

    public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getCategoryName());
        return category;
    }

    public static List<CategoryDto> mapToCategoryDtoList(List<Category> categoryList) {
        return categoryList.stream().map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }
}
