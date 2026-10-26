package com.hellohorizon.demoarij2.filter;




import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) request;
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;
        System.out.println(req.getMethod() + " " + req.getRequestURI() + " - " + duration + "ms");
        if (duration > 50) {
            System.out.println("WARNING: slow request (" + duration + "ms) on " + req.getRequestURI());
        }
    }
}