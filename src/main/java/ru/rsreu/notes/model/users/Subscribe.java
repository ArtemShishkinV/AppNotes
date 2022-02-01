package ru.rsreu.notes.model.users;

/**
 *
 */
public class Subscribe {

    private User user;
    private Tag tag;

    /**
     * @param user
     * @param tag
     */
    public Subscribe(User user, Tag tag) {
        this.user = user;
        this.tag = tag;
    }

    /**
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
