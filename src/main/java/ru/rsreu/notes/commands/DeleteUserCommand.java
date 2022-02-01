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
import java.io.IOException;

public class DeleteUserCommand extends FrontCommand {

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
        String id = request.getParameter("id");

        if (AppUtils.isParameterNull(response, id, "users")) return;

        User user = userDAO.getUserById(id);

        if (AppUtils.isEntityNull(response, user, "users")) return;

        userDAO.deleteUser(user);

        redirect("users");
    }
}
