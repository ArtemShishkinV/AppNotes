package ru.rsreu.notes.commands;

import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        User user = AppUtils.getLoginUser(session);

        if (user != null) forward("profile");
        else redirect("login");
    }
}
