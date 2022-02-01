package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.RoleDAO;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.roles.Role;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CreateUserCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
        roleDAO = daoFactory.getRoleDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        ArrayList<Role> roles = roleDAO.findAll();

        request.setAttribute("roles", roles);

        forward("createUser");
    }

    @Override
    public void send() throws ServletException, IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String roleId = request.getParameter("format");

        if (AppUtils.isParametersNull(response, "users", name, login, password, roleId)) return;

        if (userDAO.getUserByLogin(login) != null) {
            redirect("users");
            return;
        }

        User user = new User(login, password, name, roleDAO.getEntityById(Integer.parseInt(roleId)), false, false);

        userDAO.addUser(user);

        redirect("users");
    }
}
