package com.hellohorizon.demoarij2.web;

import com.hellohorizon.demoarij2.service.CartService;
import com.hellohorizon.demoarij2.service.OrderService;
import com.hellohorizon.demoarij2.service.PromotionService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CheckoutServlet - Lab 3 Step 7 refactor
 *
 * Translates HTTP requests to OrderService business operations.
 * Handles empty-cart guard exceptions gracefully.
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Inject
    private CartService cartService;

    @Inject
    private PromotionService promotionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cart", cartService.getItems());
        req.setAttribute("subtotal", cartService.getTotal());
        if (promotionService != null) {
            req.setAttribute("discountedTotal", promotionService.calculateDiscountedTotal());
        }
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        try {
            orderService.createOrder(name, email, address);
            req.setAttribute("confirmationName", name);
        } catch (IllegalStateException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }

        req.setAttribute("cart", cartService.getItems());
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }
}
