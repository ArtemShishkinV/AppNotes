package ru.rsreu.notes.dao;

import ru.rsreu.notes.model.users.User;

import java.util.ArrayList;

/**
 *
 */
public interface UserDAO {

    /**
     * @return
     */
    ArrayList<User> findAll();

    /**
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * @param login
     * @return
     */
    User getUserByLogin(String login);

    /**
     * @param user
     */
    void updateUser(User user);

    /**
     * @param user
     */
    void addUser(User user);

    /**
     * @param user
     */
    void deleteUser(User user);

}
