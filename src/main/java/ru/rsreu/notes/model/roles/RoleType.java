package ru.rsreu.notes.model.roles;

import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.dao.DAOFactory;

public enum RoleType {

    ADMIN(3), MODERATOR(2), USER(1), NONE(0);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

    public Role getRole() {
        return DAOFactory.getInstance(DBType.ORACLE).getRoleDAO().getEntityById(id);
    }

    public static Role getRole(int id) {
        return DAOFactory.getInstance(DBType.ORACLE).getRoleDAO().getEntityById(id);
    }
}
