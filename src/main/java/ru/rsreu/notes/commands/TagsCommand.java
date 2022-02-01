package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.TagDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TagsCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private TagDAO tagDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        tagDAO = daoFactory.getTagDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        session.setAttribute("tags", tagDAO.findAll());

        forward("tags");
    }

    @Override
    public void send() throws ServletException, IOException {
        String title = request.getParameter("title");

        if (!title.isEmpty()) {
            tagDAO.addTag(title);
            redirect("tags");
        } else {
            forward("tags");
        }
    }
}
