package ru.rsreu.notes.model.users;

import ru.rsreu.notes.model.Entity;

import java.util.Objects;

/**
 *
 */
public class Tag extends Entity {

    private String title;

    /**
     * @param tagID
     * @param title
     */
    public Tag(int tagID, String title) {
        super(tagID);
        this.title = title;
    }

    /**
     *
     */
    public Tag() {
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
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(title, tag.title);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Tag{" +
                "title='" + title + '\'' +
                '}';
    }
}
