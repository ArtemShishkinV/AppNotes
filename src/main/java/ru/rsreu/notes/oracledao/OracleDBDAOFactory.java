package ru.rsreu.notes.oracledao;

import ru.rsreu.notes.dao.*;

public class OracleDBDAOFactory extends DAOFactory {

    private static volatile OracleDBDAOFactory instance;

    private OracleDBDAOFactory() {

    }

    public static OracleDBDAOFactory getInstance() {
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = new OracleDBDAOFactory();
            }
        }
        return instance;
    }

    @Override
    public NoteDAO getNoteDAO() {
        return new OracleNoteDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new OracleRoleDAO();
    }

    @Override
    public TagDAO getTagDAO() {
        return new OracleTagDAO();
    }

    @Override
    public SubscribeDAO getSubscribeDAO() {
        return new OracleSubscribeDAO();
    }
}
