package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.NoteDAO;
import ru.rsreu.notes.dao.TagDAO;
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

public class SelectNoteCommand extends FrontCommand {

    private static final DAOFactory daoFactory;
    private NoteDAO noteDAO;
    private TagDAO tagDAO;
    private UserDAO userDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        noteDAO = daoFactory.getNoteDAO();
        tagDAO = daoFactory.getTagDAO();
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

        boolean hasPermission = AppUtils.hasPermissionSelectNote(user, owner);

        AppUtils.setOwnerAttribute(session, hasPermission);

        setSubscribeAttribute(session, user);
        setBackwardAttribute(session, id);
        setNoteAttributes(session, note);

        forward("selectNote");
    }

    private void setSubscribeAttribute(HttpSession session, User user) {
        session.setAttribute("subscribeTags", tagDAO.findBySubscribeUser(user));
    }

    private void setNoteAttributes(HttpSession session, Note note) {
        session.setAttribute("tags", tagDAO.findByNote(note));
        session.setAttribute("allTags", tagDAO.findAll());
        session.setAttribute("note", note);
        request.setAttribute("noteOwner", userDAO.getUserById(note.getOwnerID()).getName());
    }

    private void setBackwardAttribute(HttpSession session, String id) {
        session.setAttribute("back", request.getPathInfo().substring(1) + "?id=" + id + "&back=" + request.getParameter("back"));
    }
}
