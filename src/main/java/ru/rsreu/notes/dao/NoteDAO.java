package ru.rsreu.notes.dao;

import ru.rsreu.notes.model.notes.Note;
import ru.rsreu.notes.model.users.Tag;

import java.util.ArrayList;

/**
 *
 */
public interface NoteDAO {

    /**
     * @param id
     * @return
     */
    Note getNoteById(int id);

    /**
     * @param id
     * @return
     */
    Note getNoteById(String id);

    /**
     * @param note
     */
    void updateNote(Note note);

    /**
     * @param title
     * @param text
     * @param ownerId
     */
    void addNote(String title, String text, int ownerId);

    /**
     * @param note
     */
    void deleteNote(Note note);

    /**
     * @param note
     * @param tags
     */
    void updateNoteWithTags(Note note, ArrayList<Tag> tags);

    /**
     * @return
     */
    ArrayList<Note> findAll();

    /**
     * @param ownerId
     * @return
     */
    ArrayList<Note> findNotesByOwner(int ownerId);

    /**
     * @param tagId
     * @return
     */
    ArrayList<Note> findNotesByTag(int tagId);

    /**
     * @param blocked
     * @return
     */
    ArrayList<Note> findBlockedNotes(boolean blocked);

}
