package ru.rsreu.notes.model.users;

import ru.rsreu.notes.model.Entity;
import ru.rsreu.notes.model.roles.Role;

import java.util.Objects;

/**
 *
 */
public class User extends Entity {

    private String login;
    private String password;
    private String name;
    private Role role;
    private boolean blocked;
    private boolean statusAuthorization;

    /**
     *
     */
    public User() {
    }

    /**
     * @param login
     * @param password
     * @param name
     * @param role
     * @param blocked
     * @param statusAuthorization
     */
    public User(String login, String password, String name, Role role, boolean blocked, boolean statusAuthorization) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.blocked = blocked;
        this.statusAuthorization = statusAuthorization;
    }

    /**
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
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
    public boolean isStatusAuthorization() {
        return statusAuthorization;
    }

    /**
     * @param statusAuthorization
     */
    public void setStatusAuthorization(boolean statusAuthorization) {
        this.statusAuthorization = statusAuthorization;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return blocked == user.blocked && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(role, user.role);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, role, blocked);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
