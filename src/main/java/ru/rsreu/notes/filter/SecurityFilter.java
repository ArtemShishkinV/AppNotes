package ru.rsreu.notes.filter;

import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;
import ru.rsreu.notes.utils.SecurityUtils;
import ru.rsreu.notes.wrapper.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String pathInfo = request.getPathInfo();

        User user = AppUtils.getLoginUser(request.getSession());

        if (pathInfo.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (user != null) {
            wrapRequest = new UserRoleRequestWrapper(user, user.getRole(), request);
        }

        if (SecurityUtils.isSecurityPage(request)) {

            if (user == null) {
                response.sendRedirect("/app/login");
                return;
            }

            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);

            if (!hasPermission) {
                response.sendRedirect("/app/profile");
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }
}
