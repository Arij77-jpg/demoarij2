package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
    }

    @Test
    void testInitialEmptyCart() {
        assertTrue(cartService.getItems().isEmpty());
        assertEquals(0, cartService.getItemCount());
        assertEquals(0.0, cartService.getTotal());
    }

    @Test
    void testAddItemsAndCalculateTotal() {
        ProductDTO p1 = new ProductDTO(1L, "Laptop", "electronics", 1000.0);
        ProductDTO p2 = new ProductDTO(2L, "Mouse", "electronics", 50.0);

        cartService.addItem(p1);
        cartService.addItem(p2);

        assertEquals(2, cartService.getItemCount());
        assertEquals(1050.0, cartService.getTotal());
    }

    @Test
    void testRemoveItemById() {
        ProductDTO p1 = new ProductDTO(1L, "Laptop", "electronics", 1000.0);
        ProductDTO p2 = new ProductDTO(2L, "Mouse", "electronics", 50.0);

        cartService.addItem(p1);
        cartService.addItem(p2);

        cartService.removeItem(1L);
        assertEquals(1, cartService.getItemCount());
        assertEquals(2L, cartService.getItems().get(0).getId());
        assertEquals(50.0, cartService.getTotal());
    }

    @Test
    void testRemoveItemByIndex() {
        ProductDTO p1 = new ProductDTO(1L, "Laptop", "electronics", 1000.0);
        ProductDTO p2 = new ProductDTO(2L, "Mouse", "electronics", 50.0);

        cartService.addItem(p1);
        cartService.addItem(p2);

        cartService.removeItemByIndex(0);
        assertEquals(1, cartService.getItemCount());
        assertEquals("Mouse", cartService.getItems().get(0).getName());
    }

    @Test
    void testClearCart() {
        ProductDTO p1 = new ProductDTO(1L, "Laptop", "electronics", 1000.0);
        cartService.addItem(p1);
        assertFalse(cartService.getItems().isEmpty());

        cartService.clear();
        assertTrue(cartService.getItems().isEmpty());
        assertEquals(0.0, cartService.getTotal());
    }
}
