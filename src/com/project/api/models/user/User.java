/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.user;

import com.project.api.models.entity.Entity;
import com.project.api.models.entity.EntityAttribute;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class User extends Entity implements Serializable {

    private String username;
    private String password;
    private Date lastLoginDate;

    public User(Integer id, String username,
            String password, Date lastLoginDate) {
        super(id);
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public Object getAttribute(EntityAttribute attrib) {
        UserAttribute attribute = (UserAttribute)attrib;
        switch (attribute) {
            case USER_ID:
                return getId();
            case USERNAME:
                return getUsername();
            case PASSWORD:
                return getPassword();
            case LAST_LOGIN_DATE:
                return getLastLoginDate();
            default:
                return null;
        }
    }

    @Override
    public void setAttribute(EntityAttribute attrib, Object value) {
        UserAttribute attribute = (UserAttribute)attrib;
        switch (attribute) {
            case USER_ID:
                setId((Integer)value);
            case USERNAME:
                setUsername((String)value);
            case PASSWORD:
                setPassword((String)value);
            case LAST_LOGIN_DATE:
                setLastLoginDate((Date)value);
        }
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private Date getLastLoginDate() {
        return lastLoginDate;
    }

    private void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        return "";
    }

}
