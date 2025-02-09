package com.example.demo_oproduct.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @PrimaryKey
    private UUID id;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "description cannot be empty")
    private String description;
    @PositiveOrZero(message = "price must be a positive number")
    private double price;
    @NotEmpty(message = "category cannot be empty")
    private String category;
}
