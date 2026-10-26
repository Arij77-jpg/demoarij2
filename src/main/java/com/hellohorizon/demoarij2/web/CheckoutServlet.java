package com.hellohorizon.demoarij2.web;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        // Order creation arrives in Chapter 3 (EJB service layer).
        req.getSession().removeAttribute("cart");
        req.setAttribute("confirmationName", name);
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }
}

