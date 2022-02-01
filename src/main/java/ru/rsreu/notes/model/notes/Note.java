package ru.rsreu.notes.model.notes;

import ru.rsreu.notes.model.Entity;

/**
 *
 */
public class Note extends Entity {

    private String title;
    private String text;
    private int ownerID;
    private boolean blocked;

    /**
     *
     */
    public Note() {
    }

    /**
     * @param noteID
     * @param title
     * @param text
     * @param ownerID
     * @param blocked
     */
    public Note(int noteID, String title, String text, int ownerID, boolean blocked) {
        super(noteID);
        this.title = title;
        this.text = text;
        this.ownerID = ownerID;
        this.blocked = blocked;
    }

    /**
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * @param ownerID
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * @return
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * @param blocked
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "<h3>Note{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", ownerID=" + ownerID +
                ", blocked=" + blocked +
                '}';
    }
}
