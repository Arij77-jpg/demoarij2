package com.hellohorizon.demoarij2.repository;

import com.hellohorizon.demoarij2.model.Customer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * CustomerRepository - helper repository for managing Customer entities.
 */
@Stateless
public class CustomerRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    public Customer findOrCreate(String name, String email) {
        String cleanEmail = email != null ? email.trim() : "";
        List<Customer> list = em.createQuery(
                "SELECT c FROM Customer c WHERE LOWER(c.email) = LOWER(:email)",
                Customer.class)
                .setParameter("email", cleanEmail)
                .getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        Customer customer = new Customer(name != null ? name.trim() : "Guest", cleanEmail);
        em.persist(customer);
        return customer;
    }

    public Customer findById(Long id) {
        if (id == null) return null;
        return em.find(Customer.class, id);
    }
}
