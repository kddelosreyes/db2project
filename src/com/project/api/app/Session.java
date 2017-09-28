/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.app;

import com.project.api.models.user.User;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Session {

    private static Session session = null;
    private User loggedUser = null;

    private Session() {
    }

    public static Session get() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

}
