package ru.rsreu.notes.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.notes.dao.TagDAO;
import ru.rsreu.notes.database.ConnectionPool;
import ru.rsreu.notes.model.Entity;
import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleTagDAO implements TagDAO {
    private static final String SELECT_ALL_TAGS = ProjectResourcer.getInstance().getString("db.query.select.all.tags");
    private static final String INSERT_TAG = ProjectResourcer.getInstance().getString("db.query.insert.tag");
    private static final String SELECT_TAGS_BY_NOTE = ProjectResourcer.getInstance().getString("db.query.select.all.tags.by.note");
    private static final String SELECT_TAG_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.tag.by.id");
    private static final String DELETE_TAG = ProjectResourcer.getInstance().getString("db.query.delete.tag");
    private static final String SELECT_TAGS_BY_USER = ProjectResourcer.getInstance().getString("db.query.select.all.tags.by.user");

    @Override
    public ArrayList<Tag> findAll() {
        return giveTags(SELECT_ALL_TAGS);
    }

    @Override
    public ArrayList<Tag> findBySubscribeUser(User user) {
        return executeQueryByEntity(user, SELECT_TAGS_BY_USER);
    }

    @Override
    public ArrayList<Tag> findByNote(Note note) {
        return executeQueryByEntity(note, SELECT_TAGS_BY_NOTE);
    }

    private ArrayList<Tag> giveTags(String query) {
        ArrayList<Tag> tags = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tags.add(addTag(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tags;
    }

    @Override
    public void addTag(String title) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TAG);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Tag getTagById(int id) {
        Tag tag = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TAG_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tag = addTag(rs);
            }

            rs.close();

            return tag;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tag;
    }

    @Override
    public void deleteTag(Tag tag) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TAG);
            preparedStatement.setInt(1, tag.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Tag getTagById(String id) {
        return getTagById(Integer.parseInt(id));
    }


    private Tag addTag(ResultSet rs) throws SQLException {
        Tag tag = new Tag();

        tag.setId(rs.getInt("tag_id"));
        tag.setTitle(rs.getString("title"));

        return tag;
    }

    private ArrayList<Tag> executeQueryByEntity(Entity entity, String query) {
        ArrayList<Tag> tags = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tags.add(addTag(rs));
            }

            rs.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return tags;
    }


}
