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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AddNoteTagCommand extends FrontCommand {

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
    public void send() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        String tagId = request.getParameter("tag_id");

        if (AppUtils.isParameterNull(response, tagId, "myNotes")) return;

        ArrayList<Tag> tags = AppUtils.getNoteTags(session);

        tags.add(tagDAO.getTagById(tagId));

        session.setAttribute("editedNote", true);
        session.setAttribute("tags", tags);
    }
}
