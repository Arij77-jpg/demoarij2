package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void testGetAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(6, products.size());
    }

    @Test
    void testGetByCategory() {
        List<ProductDTO> electronics = productService.getByCategory("electronics");
        assertNotNull(electronics);
        assertEquals(3, electronics.size());
        assertTrue(electronics.stream().allMatch(p -> "electronics".equalsIgnoreCase(p.getCategory())));

        List<ProductDTO> clothing = productService.getByCategory("clothing");
        assertEquals(2, clothing.size());

        List<ProductDTO> books = productService.getByCategory("books");
        assertEquals(1, books.size());
    }

    @Test
    void testGetProductById() {
        ProductDTO product = productService.getProduct(1L);
        assertNotNull(product);
        assertEquals("Laptop Pro 14", product.getName());
        assertEquals(1299.00, product.getPrice());

        assertNull(productService.getProduct(999L));
        assertNull(productService.getProduct(null));
    }
}
