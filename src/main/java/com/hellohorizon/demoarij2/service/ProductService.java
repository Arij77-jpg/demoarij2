package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.ProductCatalog;
import com.hellohorizon.demoarij2.model.ProductDTO;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * ProductService - Lab 3 Step 1
 * Stateless session bean encapsulating product catalog operations and filtering logic.
 */
@Stateless
public class ProductService {

    public List<ProductDTO> getAllProducts() {
        return ProductCatalog.findAll();
    }

    public List<ProductDTO> getByCategory(String category) {
        if (category == null || category.isBlank()) {
            return getAllProducts();
        }
        return ProductCatalog.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category.trim()))
                .toList();
    }

    public ProductDTO getProduct(Long id) {
        return ProductCatalog.findById(id);
    }
}
