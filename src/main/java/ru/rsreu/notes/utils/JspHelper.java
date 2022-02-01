package ru.rsreu.notes.utils;

public class JspHelper {
    public static String giveUserStatusBlock(boolean blocked) {
        return blocked ? "Заблокирован" : "Разблокирован";
    }

    public static String giveSubscribeStatus(boolean subscribed) {
        if (subscribed) {
            return "Отписаться";
        } else {
            return "Подписаться";
        }
    }

    public static String giveAuthorizationStatus(boolean authorization) {
        if (authorization) {
            return "Авторизованный";
        } else {
            return "Неавторизованный";
        }
    }

}