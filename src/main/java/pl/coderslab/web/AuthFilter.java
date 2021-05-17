package pl.coderslab.web;

import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/app/*")
public class AuthFilter implements Filter {


    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Admin user = (Admin) session.getAttribute("admin");

        if (user != null) {
            chain.doFilter(req, resp);
            return;
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
