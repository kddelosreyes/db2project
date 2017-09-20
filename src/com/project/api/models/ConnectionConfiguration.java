/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models;

import com.project.api.models.constants.ConfigurationConstants;
import java.io.Serializable;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ConnectionConfiguration implements Serializable {
    
    private String connection;
    private String username;
    private String password;
    private String hostname;
    private Short port;
    private String sid;
    private Schema schema;
    
    public ConnectionConfiguration() {}
    
    public ConnectionConfiguration(String connection, String username, String password,
            String hostname, Short port, String sid) {
        this.connection = connection;
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.port = port;
        this.sid = sid;
        schema = new Schema();
    }
    
    public String getConnection() {
        return connection;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public Short getPort() {
        return port;
    }
    
    public String getSid() {
        return sid;
    }
    
    public Schema getSchema() {
    	return schema;
    }
    
    public void setConnection(String connection) {
        this.connection = connection;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    public void setPort(String port) {
        this.port = Short.valueOf(port);
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    public void setSchemaName(String schemaName) {
    	schema.setSchemaName(schemaName);
    }
    
    public void setSchemaPassword(String schemaPassword) {
    	schema.setPassword(schemaPassword);
    }
    
    @Override
    public String toString() {
        return ConfigurationConstants.CONNECTION + ":" + getConnection() + ";" +
                ConfigurationConstants.USERNAME + ":" + getUsername() + ";" + 
                ConfigurationConstants.PASSWORD + ":" + getPassword() + ";" +
                ConfigurationConstants.HOSTNAME + ":" + getHostname() + ";" +
                ConfigurationConstants.PORT + ":" + getPort() + ";" +
                ConfigurationConstants.SID + ":" + getSid() + ";" +
                ConfigurationConstants.SCHEMA_USERNAME + ":" + getSchema().getSchemaName() + ";" +
                ConfigurationConstants.SCHEMA_PASSWORD + ":" + getSchema().getPassword() + ";" +;
    }
    
}
