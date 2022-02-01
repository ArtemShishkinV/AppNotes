package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.NoteDAO;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteNoteCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private NoteDAO noteDAO;
    private UserDAO userDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        noteDAO = daoFactory.getNoteDAO();
        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);
        String id = request.getParameter("id");

        if (AppUtils.isParameterNull(response, id, "myNotes")) return;

        Note note = noteDAO.getNoteById(id);

        if (AppUtils.isEntityNull(response, note, "myNotes")) return;

        User user = AppUtils.getLoginUser(session);
        User owner = userDAO.getUserById(note.getOwnerID());

        if (!AppUtils.hasPermissionSelectNote(user, owner)) return;

        noteDAO.deleteNote(note);

        redirect("myNotes");
    }
}
