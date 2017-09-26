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
	
	public Object getAttribute(UserAttribute attribute) {
		switch(attribute) {
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
	
	public void setAttribute(UserAttribute attribute, Object value) {
		switch(attribute) {
			case USER_ID:
				setId(value);
			case USERNAME:
				setUsername(value);
			case PASSWORD:
				setPassword(value);
			case LAST_LOGIN_DATE:
				setLastLoginDate(value);
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
