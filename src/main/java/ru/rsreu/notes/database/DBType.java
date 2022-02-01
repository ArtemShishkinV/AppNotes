package ru.rsreu.notes.database;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.oracledao.OracleDBDAOFactory;

import java.sql.SQLException;

public enum DBType {

    ORACLE {

        @Override
        public DAOFactory getDAOFactory() {
            return OracleDBDAOFactory.getInstance();
        }

    };

    public abstract DAOFactory getDAOFactory();

}
