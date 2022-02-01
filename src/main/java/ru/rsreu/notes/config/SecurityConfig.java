package ru.rsreu.notes.config;

import ru.rsreu.notes.model.roles.Role;
import ru.rsreu.notes.model.roles.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {

    private static final Map<Role, ArrayList<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        ArrayList<String> userUrlPatterns = new ArrayList<>();
        userUrlPatterns.add("/myNotes");
        userUrlPatterns.add("/profile");
        userUrlPatterns.add("/tagNotes");
        userUrlPatterns.add("/navigation");
        userUrlPatterns.add("/subscribe");
        userUrlPatterns.add("/createNote");

        mapConfig.put(RoleType.USER.getRole(), userUrlPatterns);

        ArrayList<String> moderatorUrlPatterns = (ArrayList<String>) userUrlPatterns.clone();
        moderatorUrlPatterns.add("/tags");
        moderatorUrlPatterns.add("/users");
        moderatorUrlPatterns.add("/blockNotes");

        mapConfig.put(RoleType.MODERATOR.getRole(), moderatorUrlPatterns);

        ArrayList<String> adminUrlPatterns = new ArrayList<>();
        adminUrlPatterns.add("/profile");
        adminUrlPatterns.add("/users");

        mapConfig.put(RoleType.ADMIN.getRole(), adminUrlPatterns);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static ArrayList<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }
}
