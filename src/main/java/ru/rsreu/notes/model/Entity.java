package ru.rsreu.notes.model;

import java.io.Serializable;

/**
 *
 */
public abstract class Entity implements Serializable, Cloneable {

    private int id;

    /**
     *
     */
    public Entity() {
    }

    /**
     * @param id
     */
    public Entity(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
