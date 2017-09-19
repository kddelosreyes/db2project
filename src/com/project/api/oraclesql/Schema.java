/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import com.project.api.models.constants.OracleConstants;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Schema {

    private String schemaName;
    private String password;
    private Boolean hasAllPrivileges;

    public Schema() {
    }

    public Schema(String schemaName, String password, Boolean hasAllPrivileges) {
        this.schemaName = schemaName;
        this.password = password;
        this.hasAllPrivileges = hasAllPrivileges;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getHasAllPrivileges() {
        return hasAllPrivileges;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHasAllPrivileges(Boolean hasAllPrivileges) {
        this.hasAllPrivileges = hasAllPrivileges;
    }

    @Override
    public String toString() {
        return OracleConstants.SQL_CREATE_USER + getSchemaName() + OracleConstants.SQL_IDENTIFIED_BY + getPassword() + ";";
    }

    public String getAllPrivilegeQuery() {
        if (hasAllPrivileges) {
            return OracleConstants.SQL_GRANT_ALL_PRIVILEGES_TO + getSchemaName() + OracleConstants.SQL_IDENTIFIED_BY + getPassword() + ";";
        }
        return null;
    }

}
