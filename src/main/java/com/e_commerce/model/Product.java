package com.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    @NotNull(message = "Product Name is mandatory")
    @Size(min = 3, max = 30, message = "Product Name must be between 3 and 30 characters")
    private String name;


    @Column(name = "product_price")
    @NotNull(message = "Product Price is mandatory")
    private float productPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Product Category is mandatory")
    private Category productCategory;
}
