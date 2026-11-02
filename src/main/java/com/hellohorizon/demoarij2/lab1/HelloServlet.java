package com.hellohorizon.demoarij2.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HelloServlet - Lab 1: Hello Enterprise App (Step 6 & Step 9)
 *
 * What doGet does and when it runs:
 * ---------------------------------
 * doGet is an HTTP method handler provided by HttpServlet.
 * It is invoked by the Jakarta EE servlet container whenever an HTTP GET request
 * is dispatched to the mapped URL pattern ("/hello").
 * When invoked, it receives the HttpServletRequest (containing client request data)
 * and HttpServletResponse (used to construct the reply). Here, it writes the greeting
 * "Hello, Enterprise World!" to the response output stream and returns an HTTP 200 OK.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Hello, Enterprise World!");
    }
}
