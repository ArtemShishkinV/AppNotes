package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.NoteDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class TagNotesCommand extends FrontCommand {

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
        String id = request.getParameter("id");

        if (AppUtils.isParameterNull(response, id, "navigation")) return;

        ArrayList<Note> notes = noteDAO.findNotesByTag(Integer.parseInt(id));

        if (notes == null) {
            redirect("navigation");
            return;
        }

        session.setAttribute("notes", notes);
        session.setAttribute("back", request.getPathInfo().substring(1) + "?id=" + id);

        forward("tagNotes");
    }
}
