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
public enum DataType {
    
    VARCHAR(OracleConstants.SQL_VARCHAR),
    VARCHAR2(OracleConstants.SQL_VARCHAR2),
    CHAR(OracleConstants.SQL_CHAR),
    NUMBER(OracleConstants.SQL_NUMBER);
    
    private final String value;
    
    DataType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
