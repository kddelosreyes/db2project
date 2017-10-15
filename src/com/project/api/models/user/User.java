/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.user;

import com.project.api.models.Attribute;
import com.project.api.core.Model;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class User implements Model {

    private Integer id;
    private String username;
    private String password;
    private Date lastLoginDate;
    private Locale locale;

    public User(Integer id, String username, String password, Date lastLoginDate, Locale locale) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.locale = locale;
    }

    @Override
    public Object getAttribute(Attribute attribute) {
        UserAttribute attrib = (UserAttribute) attribute;
        switch (attrib) {
            case ATTRIBUTE_USER_ID:
                return getId();
            case ATTRIBUTE_USER_NAME:
                return getUsername();
            case ATTRIBUTE_USER_PASSWORD:
                return getPassword();
            case ATTRIBUTE_USER_LAST_LOGIN_DATE:
                return getLastLoginDate();
            case ATTRIBUTE_LOCALE:
            	return getLocale();
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
    
    private Locale getLocale() {
    	return locale;
    }

    @Override
    public void setAttribute(Attribute attribute, Object value) {
        UserAttribute attrib = (UserAttribute) attribute;
        switch (attrib) {
            case ATTRIBUTE_USER_ID:
                setId((Integer) value);
                break;
            case ATTRIBUTE_USER_NAME:
                setUsername((String) value);
                break;
            case ATTRIBUTE_USER_PASSWORD:
                setPassword((String) value);
                break;
            case ATTRIBUTE_USER_LAST_LOGIN_DATE:
                setLastLoginDate((Date) value);
                break;
            case ATTRIBUTE_LOCALE:
            	setLocale((Locale) value);
                break;
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
    
    private void setLocale(Locale locale) {
    	this.locale = locale;
    }

}
