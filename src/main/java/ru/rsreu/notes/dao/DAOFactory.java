package ru.rsreu.notes.dao;

import ru.rsreu.notes.database.DBType;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DBType type) {
        return type.getDAOFactory();
    }

    public abstract NoteDAO getNoteDAO();

    public abstract UserDAO getUserDAO();

    public abstract RoleDAO getRoleDAO();

    public abstract SubscribeDAO getSubscribeDAO();

    public  abstract TagDAO getTagDAO();
}
