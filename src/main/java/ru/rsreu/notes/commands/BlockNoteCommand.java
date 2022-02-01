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
import java.io.IOException;

public class BlockNoteCommand extends FrontCommand {
    private NoteDAO noteDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE);
        noteDAO = daoFactory.getNoteDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        String id = request.getParameter("id");
        String back = request.getParameter("back");

        if (AppUtils.isParametersNull(response, "myNotes", id, back)) return;

        Note note = noteDAO.getNoteById(id);

        if (AppUtils.isEntityNull(response, note, "myNotes")) return;

        note.setBlocked(!note.isBlocked());

        noteDAO.updateNote(note);

        redirect(back);
    }
}
