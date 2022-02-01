package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/users")
public class UsersCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private UserDAO userDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        session.setAttribute("users", userDAO.findAll());

        forward("users");
    }


}
