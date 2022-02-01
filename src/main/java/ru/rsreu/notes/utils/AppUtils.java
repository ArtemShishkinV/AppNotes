package ru.rsreu.notes.utils;

import ru.rsreu.notes.model.Entity;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AppUtils {

    public static User getLoginUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static void storeLoginUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    public static String getBackUrl(HttpSession session) {
        return (String) session.getAttribute("back");
    }

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession(false);
    }

    public static boolean hasPermissionSelectNote(User user, User owner) {
        return user.getId() == owner.getId();
    }

    public static boolean isEditedNote(HttpSession session) {
        return (boolean) session.getAttribute("editedNote");
    }

    public static ArrayList<Tag> getNoteTags(HttpSession session) {
        return (ArrayList<Tag>) session.getAttribute("tags");
    }

    public static boolean isParameterNull(HttpServletResponse response, String parameter, String page) throws IOException {

        if (parameter.isEmpty()) {
            redirectToErrorPage(response, page);
        }

        return parameter.isEmpty();
    }

    public static boolean isParametersNull(HttpServletResponse response, String page, String... parameters) throws IOException {
        for (String parameter : parameters) {
            if (parameter.isEmpty()) {
                redirectToErrorPage(response, page);
                return true;
            }
        }

        return false;
    }

    public static boolean isEntityNull(HttpServletResponse response, Entity entity, String page) throws IOException {
        if (entity == null) {
            redirectToErrorPage(response, page);
            return true;
        }

        return false;
    }

    public static void setOwnerAttribute(HttpSession session, boolean status) {
        session.setAttribute("isOwner", status);
    }

    private static void redirectToErrorPage(HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(page);
    }
}
