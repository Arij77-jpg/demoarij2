package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.Customer;
import com.hellohorizon.demoarij2.model.Order;
import com.hellohorizon.demoarij2.model.OrderItem;
import com.hellohorizon.demoarij2.model.Product;
import com.hellohorizon.demoarij2.model.ProductDTO;
import com.hellohorizon.demoarij2.repository.CustomerRepository;
import com.hellohorizon.demoarij2.repository.OrderRepository;
import com.hellohorizon.demoarij2.repository.ProductRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * OrderService - Lab 3 Step 6 & Chapter 4 Activity 2
 *
 * Stateless session bean defining the transactional boundary for order creation.
 * Persists Customer, Order, and OrderItem records into the database.
 */
@Stateless
public class OrderService {

    @Inject
    private NotificationService notificationService;

    @Inject
    private CartService cartService;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(String name, String email, String address) {
        // Chapter 3 Activity 3: Guard against an empty cart
        if (cartService == null || cartService.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot create order: the cart is empty.");
        }

        Order order = null;

        // Chapter 4 Activity 2: Persist order and items in database
        if (orderRepository != null && customerRepository != null) {
            Customer customer = customerRepository.findOrCreate(name, email);
            order = new Order(customer, address, cartService.getTotal());

            for (ProductDTO itemDTO : cartService.getItems()) {
                Product product = null;
                if (productRepository != null && itemDTO.getId() != null) {
                    product = productRepository.findById(itemDTO.getId());
                }
                if (product == null) {
                    product = new Product(itemDTO.getId(), itemDTO.getName(), itemDTO.getCategory(), itemDTO.getPrice());
                }
                OrderItem orderItem = new OrderItem(product, 1, itemDTO.getPrice());
                order.addItem(orderItem);
            }

            orderRepository.save(order);
        }

        if (notificationService != null) {
            notificationService.logOrderConfirmation(name);
        }

        cartService.clear();
        return order;
    }

    public List<Order> getOrdersForCustomer(Customer customer) {
        if (orderRepository != null) {
            return orderRepository.findByCustomer(customer);
        }
        return List.of();
    }
}
