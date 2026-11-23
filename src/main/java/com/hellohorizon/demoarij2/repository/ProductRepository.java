package com.hellohorizon.demoarij2.repository;

import com.hellohorizon.demoarij2.model.Product;
import com.hellohorizon.demoarij2.model.Review;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * ProductRepository - Lab 4 Step 5 & Chapter 4 Activity 3
 *
 * Stateless EJB encapsulating JPA EntityManager operations for Product entities.
 */
@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        if (findAll().isEmpty()) {
            Product p1 = new Product("Wireless Mouse", "electronics", 19.99);
            p1.addReview(new Review(5, "Super smooth and battery lasts forever!", "Amine"));
            p1.addReview(new Review(4, "Great ergonomic feel for working all day.", "Arij"));
            em.persist(p1);

            Product p2 = new Product("Mechanical Keyboard", "electronics", 79.99);
            p2.addReview(new Review(5, "Best mechanical keyboard for coding!", "Sami"));
            em.persist(p2);

            Product p3 = new Product("Desk Lamp", "home", 24.50);
            em.persist(p3);

            Product p4 = new Product("Noise Cancelling Headphones", "electronics", 149.90);
            em.persist(p4);

            Product p5 = new Product("Cotton Hoodie", "clothing", 45.00);
            em.persist(p5);

            Product p6 = new Product("Jakarta EE with JPA Guide", "books", 39.99);
            em.persist(p6);
        }
    }

    public Product findById(Long id) {
        if (id == null) {
            return null;
        }
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.id ASC", Product.class)
                .getResultList();
    }

    public List<Product> findByCategory(String category) {
        if (category == null || category.isBlank()) {
            return findAll();
        }
        return em.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.category) = LOWER(:category) ORDER BY p.id ASC",
                Product.class)
                .setParameter("category", category.trim())
                .getResultList();
    }

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    /**
     * Chapter 4 Activity 3: JPQL query with condition and named parameter.
     * Finds all products priced strictly below a given amount.
     */
    public List<Product> findProductsPricedBelow(double maxPrice) {
        return em.createQuery(
                "SELECT p FROM Product p WHERE p.price < :maxPrice ORDER BY p.price ASC",
                Product.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
}
