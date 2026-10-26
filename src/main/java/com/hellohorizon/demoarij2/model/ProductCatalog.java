package com.hellohorizon.demoarij2.model;




import java.util.List;

public class ProductCatalog {
    private static final List<ProductDTO> PRODUCTS = List.of(
            new ProductDTO(1L, "Laptop Pro 14", "electronics", 1299.00),
            new ProductDTO(2L, "Wireless Headphones", "electronics", 149.90),
            new ProductDTO(3L, "Smartphone X", "electronics", 799.00),
            new ProductDTO(4L, "Cotton T-Shirt", "clothing", 24.50),
            new ProductDTO(5L, "Denim Jacket", "clothing", 89.00),
            new ProductDTO(6L, "Jakarta EE in Practice", "books", 39.90)
    );

    public static List<ProductDTO> findAll() { return PRODUCTS; }

    public static ProductDTO findById(Long id) {
        return PRODUCTS.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}