package ru.rsreu.notes.utils;

import ru.rsreu.notes.config.SecurityConfig;
import ru.rsreu.notes.model.roles.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

public class SecurityUtils {

    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = request.getPathInfo();

        Set<Role> roles = SecurityConfig.getAllAppRoles();

        for (Role role : roles) {
            ArrayList<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);

            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = request.getPathInfo();

        Set<Role> allRoles = SecurityConfig.getAllAppRoles();

        for (Role role : allRoles) {

            if (!request.isUserInRole(role.getTitle())) {
                continue;
            }
            ArrayList<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);

            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

}
