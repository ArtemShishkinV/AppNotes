package ru.rsreu.notes.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.notes.dao.RoleDAO;
import ru.rsreu.notes.database.ConnectionPool;
import ru.rsreu.notes.model.roles.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleRoleDAO implements RoleDAO {

    private static final String SELECT_ALL_ROLES = ProjectResourcer.getInstance().getString("db.query.select.all.roles");
    private static final String SELECT_ROLE_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.role.by.id");

    @Override
    public ArrayList<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("role_id"));
                role.setTitle(resultSet.getString("title"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role getEntityById(int id) {

        Role role = new Role(id, "");

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                role.setTitle(rs.getString("title"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return role;
    }

    @Override
    public Role getEntityById(String id) {
        return getEntityById(Integer.parseInt(id));
    }
}
