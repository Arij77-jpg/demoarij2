package com.hellohorizon.demoarij2.web;


import com.hellohorizon.demoarij2.model.ProductCatalog;
import com.hellohorizon.demoarij2.model.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/cart", "/cart/add", "/cart/remove"})
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<ProductDTO> cart = (List<ProductDTO>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        if ("/cart/remove".equals(req.getServletPath())) {
            int index = Integer.parseInt(req.getParameter("index"));
            if (index >= 0 && index < cart.size()) cart.remove(index);
        } else {
            ProductDTO p = ProductCatalog.findById(Long.valueOf(req.getParameter("productId")));
            if (p != null) cart.add(p);
        }
        session.setAttribute("cart", cart);
        resp.sendRedirect(req.getContextPath() + "/cart"); // Post/Redirect/Get
    }
}