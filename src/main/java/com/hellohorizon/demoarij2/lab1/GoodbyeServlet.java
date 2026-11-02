package com.hellohorizon.demoarij2.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GoodbyeServlet - Chapter 1 Activity 1
 *
 * Second endpoint mapped to /goodbye returning a different message.
 */
@WebServlet("/goodbye")
public class GoodbyeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Goodbye, Enterprise World!");
    }
}
