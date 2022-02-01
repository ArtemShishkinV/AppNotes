package ru.rsreu.notes.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.notes.dao.UserDAO;
import ru.rsreu.notes.database.ConnectionPool;
import ru.rsreu.notes.model.roles.RoleType;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.NumericHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleUserDAO implements UserDAO {

    private static final String SELECT_ALL_USERS = ProjectResourcer.getInstance().getString("db.query.select.all.users");
    private static final String SELECT_USER_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.user.by.id");
    private static final String UPDATE_USER = ProjectResourcer.getInstance().getString("db.query.update.user");
    private static final String ADD_USER = ProjectResourcer.getInstance().getString("db.query.insert.user");
    private static final String IS_EXIST_USER = ProjectResourcer.getInstance().getString("db.query.select.user.by.login");
    private static final String DELETE_USER = ProjectResourcer.getInstance().getString("db.query.delete.user");

    @Override
    public ArrayList<User> findAll() {

        ArrayList<User> users = new ArrayList<>();
        OracleRoleDAO roleDAO = new OracleRoleDAO();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(roleDAO.getEntityById(rs.getInt("role_id")));
                user.setBlocked(NumericHelper.convertToBool(rs.getInt("blocked")));
                user.setStatusAuthorization(NumericHelper.convertToBool((rs.getInt("status"))));
                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }

            rs.close();

            return user;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(String id) {
        return getUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            replaceUser(preparedStatement, user);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getRole().getId());
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_USER);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setName(resultSet.getString(4));
        user.setRole(RoleType.getRole(resultSet.getInt(5)));
        user.setBlocked(NumericHelper.convertToBool(resultSet.getInt(6)));
        user.setStatusAuthorization(NumericHelper.convertToBool(resultSet.getInt(7)));
        return user;
    }

    private void replaceUser(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setInt(4, user.getRole().getId());
        preparedStatement.setInt(5, NumericHelper.convertToInt(user.isBlocked()));
        preparedStatement.setInt(6, NumericHelper.convertToInt(user.isStatusAuthorization()));
        preparedStatement.setInt(7, user.getId());
    }

}
