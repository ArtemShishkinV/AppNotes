package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.NoteDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyNotesCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private NoteDAO noteDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        noteDAO = daoFactory.getNoteDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        User user = AppUtils.getLoginUser(session);

        setNoteAttribute(session, user);

        forward("myNotes");
    }

    private void setNoteAttribute(HttpSession session, User user) {
        session.setAttribute("notes", noteDAO.findNotesByOwner(user.getId()));
        session.setAttribute("editedNote", false);
        session.setAttribute("back", request.getPathInfo().substring(1));
    }
}
