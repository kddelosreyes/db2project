/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models;

import com.project.api.oraclesql.DataType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Session {
	
	private static Session session = null;
	private static User loggedUser = null;
	
	private Session() {
	}
	
	public Session get() {
		if(session == null) {
			session = new Session();
		}
		return session;
	}
    
	private static User getLoggedUser() {
    	return loggedUser;
    }
    
    private static void setLoggedUser(User user) {
    	this.user = user;
    }
    
}
