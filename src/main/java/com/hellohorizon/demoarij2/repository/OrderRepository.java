package com.hellohorizon.demoarij2.repository;

import com.hellohorizon.demoarij2.model.Customer;
import com.hellohorizon.demoarij2.model.Order;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * OrderRepository - Chapter 4 Activity 2
 *
 * Persists orders and associated order items, and fetches orders for a customer.
 */
@Stateless
public class OrderRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    public void save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
    }

    public Order findById(Long id) {
        if (id == null) {
            return null;
        }
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o ORDER BY o.id DESC", Order.class)
                .getResultList();
    }

    public List<Order> findByCustomer(Customer customer) {
        if (customer == null) {
            return List.of();
        }
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.customer = :customer ORDER BY o.id DESC",
                Order.class)
                .setParameter("customer", customer)
                .getResultList();
    }

    public List<Order> findByCustomerEmail(String email) {
        if (email == null || email.isBlank()) {
            return List.of();
        }
        return em.createQuery(
                "SELECT o FROM Order o WHERE LOWER(o.customer.email) = LOWER(:email) ORDER BY o.id DESC",
                Order.class)
                .setParameter("email", email.trim())
                .getResultList();
    }
}
