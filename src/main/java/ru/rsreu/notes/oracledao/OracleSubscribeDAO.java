package ru.rsreu.notes.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.notes.dao.SubscribeDAO;
import ru.rsreu.notes.database.ConnectionPool;
import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleSubscribeDAO implements SubscribeDAO {

    private static final String SELECT_SUBSCRIBE = ProjectResourcer.getInstance().getString("db.query.select.subscribe");
    private static final String ADD_SUBSCRIBE = ProjectResourcer.getInstance().getString("db.query.insert.subscribe");
    private static final String DELETE_SUBSCRIBE = ProjectResourcer.getInstance().getString("db.query.delete.subscribe");

    @Override
    public void addSubscribe(User user, Tag tag) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUBSCRIBE);
            preparedStatement.setInt(1, tag.getId());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteSubscribe(User user, Tag tag) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBSCRIBE);
            preparedStatement.setInt(1, tag.getId());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isSubscribe(User user, Tag tag) {

        Note note = new Note();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIBE);
            preparedStatement.setInt(1, tag.getId());
            preparedStatement.setInt(2, user.getId());
            ResultSet rs = preparedStatement.executeQuery();


            return rs.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

