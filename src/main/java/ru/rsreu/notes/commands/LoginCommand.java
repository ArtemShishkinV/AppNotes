package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {

    private UserDAO userDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        userDAO = factory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        User user = AppUtils.getLoginUser(session);

        if (user != null) {
            redirect("profile");
        } else {
            forward("login");
        }
    }

    @Override
    public void send() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (AppUtils.isParametersNull(response, "login", login, password)) return;

        User user = this.userDAO.getUserByLogin(login);

        if (user == null || !user.getPassword().equals(password) || user.isBlocked()) {
            forward("login");
        } else {
            HttpSession session = request.getSession();

            user.setStatusAuthorization(true);
            userDAO.updateUser(user);

            AppUtils.storeLoginUser(session, user);

            redirect("profile");
        }
    }
}

