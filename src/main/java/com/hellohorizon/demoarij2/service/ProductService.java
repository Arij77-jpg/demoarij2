package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.Product;
import com.hellohorizon.demoarij2.model.ProductCatalog;
import com.hellohorizon.demoarij2.model.ProductDTO;
import com.hellohorizon.demoarij2.repository.ProductRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

/**
 * ProductService - Lab 4 Step 6 & Chapter 4 Activity 3
 *
 * Stateless session bean wired to ProductRepository.
 * Delegates data retrieval to the database persistence layer,
 * with fallback for standalone unit testing environments.
 */
@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        if (productRepository != null) {
            List<Product> products = productRepository.findAll();
            if (!products.isEmpty()) {
                return products.stream().map(Product::toDTO).toList();
            }
        }
        return ProductCatalog.findAll();
    }

    public List<ProductDTO> getByCategory(String category) {
        if (productRepository != null) {
            List<Product> products = productRepository.findByCategory(category);
            if (!products.isEmpty()) {
                return products.stream().map(Product::toDTO).toList();
            }
        }
        if (category == null || category.isBlank()) {
            return getAllProducts();
        }
        return ProductCatalog.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category.trim()))
                .toList();
    }

    public ProductDTO getProduct(Long id) {
        if (productRepository != null) {
            Product p = productRepository.findById(id);
            if (p != null) {
                return p.toDTO();
            }
        }
        return ProductCatalog.findById(id);
    }

    /**
     * Chapter 4 Activity 3: Filter products priced strictly below a given threshold.
     */
    public List<ProductDTO> getProductsPricedBelow(double maxPrice) {
        if (productRepository != null) {
            return productRepository.findProductsPricedBelow(maxPrice).stream()
                    .map(Product::toDTO)
                    .toList();
        }
        return ProductCatalog.findAll().stream()
                .filter(p -> p.getPrice() < maxPrice)
                .toList();
    }
}
