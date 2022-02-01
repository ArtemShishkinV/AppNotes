package ru.rsreu.notes.dao;

import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;

/**
 *
 */
public interface SubscribeDAO {

    /**
     * @param user
     * @param tag
     * @return
     */
    boolean isSubscribe(User user, Tag tag);

    /**
     * @param user
     * @param tag
     */
    void addSubscribe(User user, Tag tag);

    /**
     * @param user
     * @param tag
     */
    void deleteSubscribe(User user, Tag tag);
}
