package com.hellohorizon.demoarij2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityModelTest {

    @Test
    void testProductAndReviewRelationship() {
        Product p = new Product(1L, "Test Product", "electronics", 99.99);
        Review r1 = new Review(5, "Amazing quality", "Alice");
        Review r2 = new Review(4, "Very good", "Bob");

        p.addReview(r1);
        p.addReview(r2);

        assertEquals(2, p.getReviews().size());
        assertEquals(p, r1.getProduct());
        assertEquals(p, r2.getProduct());
        assertEquals("Alice", p.getReviews().get(0).getAuthor());

        ProductDTO dto = p.toDTO();
        assertEquals(1L, dto.getId());
        assertEquals("Test Product", dto.getName());
        assertEquals(2, dto.getReviews().size());
    }

    @Test
    void testCustomerOrderRelationship() {
        Customer c = new Customer("John Doe", "john@example.com");
        Order o = new Order(c, "123 Main St", 150.0);
        Product p = new Product(10L, "Widget", "tools", 50.0);
        OrderItem item = new OrderItem(p, 3, 50.0);

        o.addItem(item);
        c.addOrder(o);

        assertEquals(1, c.getOrders().size());
        assertEquals(1, o.getItems().size());
        assertEquals(o, item.getOrder());
        assertEquals(p, item.getProduct());
        assertEquals(3, item.getQuantity());
        assertEquals(c, o.getCustomer());
    }
}
