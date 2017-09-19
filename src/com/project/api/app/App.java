/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.app;

import com.project.api.oraclesql.OracleConnection;
import com.project.api.oraclesql.Table;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class App {
    
    private static OracleConnection oracleConnection = null;
    private static ArrayList<Table> tables = null;
    
    public static Connection getConnection() throws IOException, FileNotFoundException {
        if(oracleConnection == null) {
            oracleConnection = new OracleConnection();
        }
        
        return oracleConnection.getConnection();
    }
    
    public static ArrayList<Table> getTables() {
        if(tables == null) {
            tables = new ArrayList<>();
        }
        
        
        
        return tables;
    }
    
}
