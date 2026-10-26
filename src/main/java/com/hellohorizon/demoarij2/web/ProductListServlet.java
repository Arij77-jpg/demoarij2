package com.hellohorizon.demoarij2.web;

import com.hellohorizon.demoarij2.model.ProductCatalog;
import com.hellohorizon.demoarij2.model.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalog/products")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String category = req.getParameter("category");
        List<ProductDTO> products = ProductCatalog.findAll();
        if (category != null && !category.isBlank()) {
            products = products.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(category))
                    .toList();
        }
        req.setAttribute("products", products);
        req.setAttribute("selectedCategory", category);
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}