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

/**
 * ProductDetailServlet - Lab 3 Step 2 refactor
 * Injects ProductService to fetch product by id.
 */
@WebServlet("/catalog/product")
public class ProductDetailServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProductDTO product = null;
        try {
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.isBlank()) {
                product = productService.getProduct(Long.valueOf(idParam));
            }
        } catch (NumberFormatException e) {
            // invalid id format handled below
        }

        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return;
        }

        req.setAttribute("product", product);
        req.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(req, resp);
    }
}
