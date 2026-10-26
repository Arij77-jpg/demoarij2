
package com.hellohorizon.demoarij2.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/cart/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Placeholder: real auth check arrives in Chapter 6.
        chain.doFilter(request, response);
    }
}