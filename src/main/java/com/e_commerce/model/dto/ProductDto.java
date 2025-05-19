package com.e_commerce.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;
    private float productPrice;
    private String categoryName;

}
