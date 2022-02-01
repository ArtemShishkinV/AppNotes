package ru.rsreu.notes.dao;

import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;

import java.util.ArrayList;

/**
 *
 */
public interface TagDAO {

    /**
     * @return
     */
    ArrayList<Tag> findAll();

    /**
     * @param id
     * @return
     */
    Tag getTagById(int id);

    /**
     * @param id
     * @return
     */
    Tag getTagById(String id);

    /**
     * @param title
     */
    void addTag(String title);

    /**
     * @param tag
     */
    void deleteTag(Tag tag);

    /**
     * @param note
     * @return
     */
    ArrayList<Tag> findByNote(Note note);

    /**
     * @param user
     * @return
     */
    ArrayList<Tag> findBySubscribeUser(User user);
}
