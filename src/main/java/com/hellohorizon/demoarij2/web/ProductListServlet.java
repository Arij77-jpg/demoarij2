package com.hellohorizon.demoarij2.web;

import com.hellohorizon.demoarij2.model.ProductDTO;
import com.hellohorizon.demoarij2.service.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ProductListServlet - Lab 3 Step 2 refactor
 * Delegates product querying and filtering to injected ProductService.
 */
@WebServlet("/catalog/products")
public class ProductListServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String category = req.getParameter("category");
        List<ProductDTO> products = (category != null && !category.isBlank())
                ? productService.getByCategory(category)
                : productService.getAllProducts();

        req.setAttribute("products", products);
        req.setAttribute("selectedCategory", category);
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}
