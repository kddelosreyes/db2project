/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import com.project.api.app.Property;
import com.project.api.app.Property.PropertyPath;
import com.project.api.models.ConnectionConfiguration;
import com.project.api.models.constants.ConfigurationConstants;
import com.project.api.utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class OracleConnection {

    private static Connection connection = null;
    private static ConnectionConfiguration connectionConfig = null;

    private final String ORACLE_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public OracleConnection() throws IOException, FileNotFoundException {
        if (connection == null) {
            getConnectionParameters();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ConnectionConfiguration getConnectionConfiguration() {
        return connectionConfig;
    }

    private void getConnectionParameters() throws IOException,
            FileNotFoundException {
        BufferedReader br = FileUtils.getFileReader(Property.getPropertyFile(
                PropertyPath.CONFIGURATION).getPathFile());
        String content = "";
        connectionConfig = new ConnectionConfiguration();

        while ((content = br.readLine()) != null) {
            if (content.contains(ConfigurationConstants.SCHEMA_USERNAME)) {
                connectionConfig.setSchemaName(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.SCHEMA_PASSWORD)) {
                connectionConfig.setSchemaPassword(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.CONNECTION)) {
                connectionConfig.setConnection(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.USERNAME)) {
                connectionConfig.setUsername(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.PASSWORD)) {
                connectionConfig.setPassword(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.HOSTNAME)) {
                connectionConfig.setHostname(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.PORT)) {
                connectionConfig.setPort(FileUtils.getValue(content));
            } else if (content.contains(ConfigurationConstants.SID)) {
                connectionConfig.setSid(FileUtils.getValue(content));
            }
        }

        if (connection == null) {
            try {
                Class.forName(ORACLE_JDBC_DRIVER);
                String url = connectionConfig.toString().split("\\|")[0], username = connectionConfig
                        .toString().split("\\|")[1], password = connectionConfig
                        .toString().split("\\|")[2];
                connection = DriverManager.getConnection(url, username,
                        password);
            } catch (ClassNotFoundException exc) {
                System.out.println("ClassNotFoundException");
            } catch (SQLException exc) {
                System.out.println("SQLException");
            }
        }
    }

}
