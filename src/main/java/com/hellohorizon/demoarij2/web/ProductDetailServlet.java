package com.hellohorizon.demoarij2.web;

import com.hellohorizon.demoarij2.model.ProductCatalog;
import com.hellohorizon.demoarij2.model.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/catalog/product")
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProductDTO product = null;
        try {
            product = ProductCatalog.findById(Long.valueOf(req.getParameter("id")));
        } catch (NumberFormatException e) {
            // invalid or missing id, handled below
        }
        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return;
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(req, resp);
    }
}