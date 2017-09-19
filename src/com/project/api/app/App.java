/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.app;

import com.project.api.oraclesql.OracleConnection;
import com.project.api.oraclesql.Table;
import com.project.api.utils.FileUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class App {

    private static OracleConnection oracleConnection = null;
    private static ArrayList<Table> tables = null;

    private static final String PROJECT_FILE = "project.properties";

    public static Connection getConnection() throws IOException, FileNotFoundException {
        if (oracleConnection == null) {
            oracleConnection = new OracleConnection();
        }

        return oracleConnection.getConnection();
    }

    public static ArrayList<Table> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();
        }

        try {
            for (String tableName : getTableFromConfig()) {

            }
        } catch (FileNotFoundException exc) {
            System.out.println("FileNotFoundException: " + exc.getMessage());
        } catch (IOException exc) {
            System.out.println("IOException: " + exc.getMessage());
        }

        return tables;
    }

    private static List<String> getTableFromConfig() throws IOException, FileNotFoundException {
        List<String> tableNames = new ArrayList<>();
        BufferedReader br = FileUtils.getFileReader(PROJECT_FILE);
        String content = "";
        while ((content = br.readLine()) != null) {
            tableNames.add(content);
        }
        return tableNames;
    }

}
