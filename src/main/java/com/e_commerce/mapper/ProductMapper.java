package com.e_commerce.mapper;

import com.e_commerce.model.Category;
import com.e_commerce.model.Product;
import com.e_commerce.model.dto.ProductDto;
import com.e_commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setCategoryName(product.getProductCategory().getName());
        return productDto;
    }

    public Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        Optional<Category> category = categoryRepository.findByName(productDto.getCategoryName());
        category.ifPresent(product::setProductCategory);
        return product;
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream().map(this::mapToProductDto).collect(Collectors.toList());
    }
}
