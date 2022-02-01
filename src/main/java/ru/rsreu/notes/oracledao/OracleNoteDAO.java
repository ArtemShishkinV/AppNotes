package ru.rsreu.notes.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.notes.dao.NoteDAO;
import ru.rsreu.notes.database.ConnectionPool;
import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.utils.NumericHelper;

import java.sql.*;
import java.util.ArrayList;

public class OracleNoteDAO implements NoteDAO {
    private static final String SELECT_ALL_NOTES = ProjectResourcer.getInstance().getString("db.query.select.all.notes");
    private static final String INSERT_NOTE = ProjectResourcer.getInstance().getString("db.query.insert.note");
    private static final String SELECT_NOTE_BY_OWNER = ProjectResourcer.getInstance().getString("db.query.select.all.notes.by.owner.id");
    private static final String DELETE_NOTE = ProjectResourcer.getInstance().getString("db.query.delete.note");
    private static final String SELECT_NOTE_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.note.by.id");
    private static final String UPDATE_NOTE = ProjectResourcer.getInstance().getString("db.query.update.note");
    private static final String DELETE_NOTETAGS_BY_ID = ProjectResourcer.getInstance().getString("db.query.delete.notetags");
    private static final String INSERT_NOTETAGS = ProjectResourcer.getInstance().getString("db.query.insert.notetags");
    private static final String SELECT_NOTES_BY_TAG = ProjectResourcer.getInstance().getString("db.query.select.notes.by.tag");
    private static final String SELECT_BLOCKED_NOTES = ProjectResourcer.getInstance().getString("db.query.select.blocked.notes");

    public OracleNoteDAO() {

    }

    @Override
    public ArrayList<Note> findAll() {

        ArrayList<Note> notes = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            Statement preparedStatement = connection.createStatement();
            ResultSet rs = preparedStatement.executeQuery(SELECT_ALL_NOTES);

            appendNotes(notes, rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return notes;
    }

    @Override
    public Note getNoteById(int id) {

        Note note = new Note();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                note = giveNote(rs);
            }

            rs.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return note;
    }

    @Override
    public Note getNoteById(String id) {
        return getNoteById(Integer.parseInt(id));
    }

    @Override
    public void updateNote(Note note) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NOTE);

            replaceNote(preparedStatement, note);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addNote(String title, String text, int ownerId) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTE);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, ownerId);
            preparedStatement.setInt(4, 0);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Note> findNotesByTag(int tagId) {
        ArrayList<Note> notes = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_TAG);
            preparedStatement.setInt(1, tagId);

            ResultSet rs = preparedStatement.executeQuery();
            appendNotes(notes, rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return notes;
    }

    @Override
    public void deleteNote(Note note) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTE);
            preparedStatement.setInt(1, note.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateNoteWithTags(Note note, ArrayList<Tag> tags) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTETAGS_BY_ID);
            preparedStatement.setInt(1, note.getId());
            preparedStatement.executeUpdate();

            for (Tag tag : tags) {
                preparedStatement = connection.prepareStatement(INSERT_NOTETAGS);
                preparedStatement.setInt(1, tag.getId());
                preparedStatement.setInt(2, note.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Note> findBlockedNotes(boolean blocked) {
        ArrayList<Note> notes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BLOCKED_NOTES);
            preparedStatement.setInt(1, NumericHelper.convertToInt(blocked));

            ResultSet rs = preparedStatement.executeQuery();
            appendNotes(notes, rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return notes;
    }

    @Override
    public ArrayList<Note> findNotesByOwner(int ownerId) {
        ArrayList<Note> notes = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTE_BY_OWNER);
            preparedStatement.setInt(1, ownerId);

            ResultSet rs = preparedStatement.executeQuery();

            appendNotes(notes, rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return notes;
    }


    private void appendNotes(ArrayList<Note> notes, ResultSet rs) throws SQLException {
        while (rs.next()) {
            notes.add(giveNote(rs));
        }
    }

    private Note giveNote(ResultSet rs) throws SQLException {
        Note note = new Note();

        note.setId(rs.getInt("note_id"));
        note.setTitle(rs.getString("title"));
        note.setText(rs.getString("text"));
        note.setOwnerID(rs.getInt("owner_id"));
        note.setBlocked(NumericHelper.convertToBool(rs.getInt("blocked")));

        return note;
    }

    private void replaceNote(PreparedStatement preparedStatement, Note note) throws SQLException {
        preparedStatement.setString(1, note.getTitle());
        preparedStatement.setString(2, note.getText());
        preparedStatement.setInt(3, note.getOwnerID());
        preparedStatement.setInt(4, NumericHelper.convertToInt(note.isBlocked()));
        preparedStatement.setInt(5, note.getId());
    }


}
