/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.user;

import java.io.Serializable;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class User implements Serializable {
    
	private String username;
	private String password;
	private Date lastLoginDate;
	
	public User(String username, String password, Date lastLoginDate) {
		this.username = username;
		this.password = password;
		this.lastLoginDate = lastLoginDate;
	}
	
	public Object getAttribute(UserAttribute attribute) {
		switch(attribute) {
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
	
	public void setAttribute(UserAttribute attribute, Object value) {
		switch(attribute) {
			case USERNAME:
				return setUsername(value);
			case PASSWORD:
				return setPassword(value);
			case LAST_LOGIN_DATE:
				return setLastLoginDate(value);
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}
