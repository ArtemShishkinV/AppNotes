package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.TagDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTagCommand extends FrontCommand {

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
        String id = request.getParameter("id");

        if (AppUtils.isParameterNull(response, id, "tags")) return;

        Tag tag = tagDAO.getTagById(id);

        if (AppUtils.isEntityNull(response, tag, "tags")) return;

        tagDAO.deleteTag(tag);

        redirect("tags");
    }
}
