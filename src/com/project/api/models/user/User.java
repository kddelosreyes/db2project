/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.user;

import java.util.Date;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private Date lastLoginDate;

    public User(Integer id, String username, String password, Date lastLoginDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

    public Object getAttribute(UserAttribute attribute) {
        switch (attribute) {
            case ATTRIBUTE_USER_ID:
                return getId();
            case ATTRIBUTE_USER_NAME:
                return getUsername();
            case ATTRIBUTE_USER_PASSWORD:
                return getPassword();
            case ATTRIBUTE_USER_LAST_LOGIN_DATE:
                return getLastLoginDate();
        }
        return null;
    }

    private Integer getId() {
        return id;
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    private Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setAttribute(UserAttribute attribute, Object value) {
        switch (attribute) {
            case ATTRIBUTE_USER_ID:
                setId((Integer) value);
            case ATTRIBUTE_USER_NAME:
                setUsername((String) value);
            case ATTRIBUTE_USER_PASSWORD:
                setPassword((String) value);
            case ATTRIBUTE_USER_LAST_LOGIN_DATE:
                setLastLoginDate((Date) value);
        }
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

}
