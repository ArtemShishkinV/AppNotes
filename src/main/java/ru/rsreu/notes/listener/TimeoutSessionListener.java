package ru.rsreu.notes.listener;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TimeoutSessionListener implements HttpSessionListener {

    private static final int MAX_INACTIVE_INTERVAL = 30 * 60;

    private final UserDAO userDAO = DAOFactory.getInstance(DBType.ORACLE).getUserDAO();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = AppUtils.getLoginUser(session);

        user.setStatusAuthorization(false);

        userDAO.updateUser(user);
    }
}
