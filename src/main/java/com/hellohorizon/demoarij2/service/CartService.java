package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.ProductDTO;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CartService - Lab 3 Step 3
 * Stateful, session-scoped enterprise bean managing shopping cart state per user session.
 * Implements Serializable to allow the container to persist/passivate state.
 */
@Stateful
@SessionScoped
@Named("cartService")
public class CartService implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ProductDTO> items = new ArrayList<>();

    public void addItem(ProductDTO product) {
        if (product != null) {
            items.add(product);
        }
    }

    public void removeItem(Long productId) {
        if (productId != null) {
            items.removeIf(p -> productId.equals(p.getId()));
        }
    }

    public void removeItemByIndex(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(ProductDTO::getPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }
}
