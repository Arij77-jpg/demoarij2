package com.hellohorizon.demoarij2.web;

import com.hellohorizon.demoarij2.model.ProductDTO;
import com.hellohorizon.demoarij2.service.CartService;
import com.hellohorizon.demoarij2.service.ProductService;
import com.hellohorizon.demoarij2.service.PromotionService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CartServlet - Lab 3 Step 4 refactor
 *
 * Injects CartService (@Stateful, @SessionScoped) and ProductService (@Stateless).
 * The servlet no longer touches HttpSession directly; CartService handles state.
 */
@WebServlet({"/cart", "/cart/add", "/cart/remove"})
public class CartServlet extends HttpServlet {

    @Inject
    private CartService cartService;

    @Inject
    private ProductService productService;

    @Inject
    private PromotionService promotionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cart", cartService.getItems());
        req.setAttribute("subtotal", cartService.getTotal());
        if (promotionService != null) {
            req.setAttribute("discount", promotionService.calculateDiscountAmount());
            req.setAttribute("discountedTotal", promotionService.calculateDiscountedTotal());
        }
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();

        if ("/cart/remove".equals(servletPath)) {
            String indexParam = req.getParameter("index");
            String productIdParam = req.getParameter("productId");
            if (indexParam != null && !indexParam.isBlank()) {
                try {
                    int index = Integer.parseInt(indexParam);
                    cartService.removeItemByIndex(index);
                } catch (NumberFormatException ignored) {}
            } else if (productIdParam != null && !productIdParam.isBlank()) {
                try {
                    Long productId = Long.valueOf(productIdParam);
                    cartService.removeItem(productId);
                } catch (NumberFormatException ignored) {}
            }
        } else {
            String productIdParam = req.getParameter("productId");
            if (productIdParam != null && !productIdParam.isBlank()) {
                try {
                    Long productId = Long.valueOf(productIdParam);
                    ProductDTO product = productService.getProduct(productId);
                    if (product != null) {
                        cartService.addItem(product);
                    }
                } catch (NumberFormatException ignored) {}
            }
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
