package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.NoteDAO;
import ru.rsreu.notes.dao.TagDAO;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.roles.RoleType;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class EditNoteCommand extends FrontCommand {

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

        if (user.getRole().equals(RoleType.MODERATOR.getRole()) || hasPermission) {
            if (!AppUtils.isEditedNote(session)) setNoteTagsAttribute(session, note);

            setNoteAvailableTagsAttributes(session, note);
            setOwnerNameAttribute(owner);
            setBackwardAttribute(session, id);

            forward("editNote");
        } else {
            redirect(request.getParameter("back"));
        }
    }


    @Override
    public void send() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String text = request.getParameter("text");

        Note note = noteDAO.getNoteById(id);

        if (AppUtils.isEntityNull(response, note, "myNotes")) return;

        note.setText(text);
        note.setTitle(title);

        noteDAO.updateNote(note);
        noteDAO.updateNoteWithTags(note, AppUtils.getNoteTags(session));

        setBackwardAttribute(session, id);

        redirect(request.getParameter("back"));
    }

    private void setNoteAvailableTagsAttributes(HttpSession session, Note note) {
        session.setAttribute("allTags", availableTags(session));
        session.setAttribute("note", note);
    }

    private void setNoteTagsAttribute(HttpSession session, Note note) {
        session.setAttribute("tags", tagDAO.findByNote(note));
    }

    private void setOwnerNameAttribute(User owner) {
        request.setAttribute("noteOwner", owner.getName());
    }

    private void setBackwardAttribute(HttpSession session, String id) {
        session.setAttribute("back", request.getPathInfo().substring(1) + "?id=" + id + "&back=" + request.getParameter("back"));
    }

    private ArrayList<Tag> availableTags(HttpSession session) {
        ArrayList<Tag> availableTags = tagDAO.findAll();
        ArrayList<Tag> currentTags = AppUtils.getNoteTags(session);

        availableTags.removeAll(currentTags);

        return availableTags;
    }
}
