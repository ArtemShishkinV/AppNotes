package ru.rsreu.notes.commands;

import ru.rsreu.notes.dao.DAOFactory;
import ru.rsreu.notes.dao.SubscribeDAO;
import ru.rsreu.notes.dao.TagDAO;
import ru.rsreu.notes.database.DBType;
import ru.rsreu.notes.model.users.Tag;
import ru.rsreu.notes.model.users.User;
import ru.rsreu.notes.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubscribeTagCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private SubscribeDAO subscribeDAO;
    private TagDAO tagDAO;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        subscribeDAO = daoFactory.getSubscribeDAO();
        tagDAO = daoFactory.getTagDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = AppUtils.getSession(request);

        String id = request.getParameter("id");
        String back = AppUtils.getBackUrl(session);

        if (AppUtils.isParameterNull(response, id, back)) return;

        User user = AppUtils.getLoginUser(session);
        Tag tag = tagDAO.getTagById(id);

        if (subscribeDAO.isSubscribe(user, tag)) {
            subscribeDAO.deleteSubscribe(user, tag);
        } else {
            subscribeDAO.addSubscribe(user, tag);
        }

        redirect(back);
    }
}
