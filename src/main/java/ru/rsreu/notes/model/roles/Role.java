package ru.rsreu.notes.model.roles;

import ru.rsreu.notes.model.Entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 */
public class Role extends Entity implements Serializable {

    private String title;

    /**
     * @param roleID
     * @param title
     */
    public Role(int roleID, String title) {
        super(roleID);
        this.title = title;
    }

    /**
     *
     */
    public Role() {
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
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(title, role.title);
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
        return "Role{" +
                "title='" + title + '\'' +
                '}';
    }
}
