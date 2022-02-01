package ru.rsreu.notes.dao;

import ru.rsreu.notes.model.roles.Role;

import java.util.ArrayList;

/**
 *
 */
public interface RoleDAO {

    /**
     * @return
     */
    ArrayList<Role> findAll();

    /**
     * @param id
     * @return
     */
    Role getEntityById(int id);

    /**
     * @param id
     * @return
     */
    Role getEntityById(String id);
}
