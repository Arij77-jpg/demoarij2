package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class PromotionServiceTest {

    private PromotionService promotionService;
    private CartService cartService;

    @BeforeEach
    void setUp() throws Exception {
        promotionService = new PromotionService();
        cartService = new CartService();

        // Inject cartService via reflection for standalone unit test
        Field field = PromotionService.class.getDeclaredField("cartService");
        field.setAccessible(true);
        field.set(promotionService, cartService);
    }

    @Test
    void testTenPercentDiscountCalculation() {
        cartService.addItem(new ProductDTO(1L, "Item 1", "electronics", 100.0));
        cartService.addItem(new ProductDTO(2L, "Item 2", "clothing", 100.0));

        // Subtotal = 200.0
        assertEquals(200.0, cartService.getTotal());

        // 10% discount = 20.0
        assertEquals(20.0, promotionService.calculateDiscountAmount(), 0.001);

        // Discounted total = 180.0
        assertEquals(180.0, promotionService.calculateDiscountedTotal(), 0.001);
    }

    @Test
    void testApplyDiscountArbitraryAmount() {
        assertEquals(90.0, promotionService.applyDiscount(100.0), 0.001);
        assertEquals(45.0, promotionService.applyDiscount(50.0), 0.001);
    }
}
