/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.api.app.Property.PropertyPath;
import com.project.api.models.user.User;
import com.project.api.oraclesql.DataType;
import com.project.api.oraclesql.OracleConnection;
import com.project.api.oraclesql.Schema;
import com.project.api.oraclesql.Select;
import com.project.api.oraclesql.Sequence;
import com.project.api.oraclesql.Table;
import com.project.api.oraclesql.TableColumn;
import com.project.api.utils.FileUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class App {

    private static OracleConnection oracleConnection = null;
    private static List<Table> tables = null;
    private static List<String> tableNames = null;
    private static List<String> sequenceNames = null;

    private static final int IDX_TABLE_NAME = 3;

    private static final Boolean TRUE = true;
    private static final Boolean FALSE = false;

    private static final String TABLE_IDX = "TABLE=";
    private static final String SEQUENCE_IDX = "SEQUENCE=";
    
    private static final String APP = "App.java";
    private static final String GET_CONNECTION = "getConnection";
    private static final String GET_TABLES = "getTables";
    private static final String GET_SEQUENCES = "getSequences";
    private static final String GET_SEQUENCES_FROM_PROPERTIES = "getSequencesFromProperties";
    private static final String GET_TABLES_FROM_PROPERTIES = "getTablesFromProperties";
    private static final String GET_LOGGED_USER = "getLoggedUser";
    private static final String SET_LOGGED_USER = "setLoggedUser";

    private static final Logger LOG = Logger.getLogger("App");

    public static Connection getConnection() throws IOException,
            FileNotFoundException {
        if (oracleConnection == null) {
            oracleConnection = new OracleConnection();
            log(Level.INFO, APP, GET_CONNECTION, "Creating OracleConnection instance.");
        }

        return oracleConnection.getConnection();
    }

    public static List<Table> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();
            log(Level.INFO, APP, GET_TABLES, "Instantiating list of tables for application.");
        } else {
            log(Level.INFO, APP, GET_TABLES, "Returning " + tables.size() + " tables.");
            return tables;
        }

        Schema schema = null;
        try {
            Connection connection = getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            schema = oracleConnection.getConnectionConfiguration().getSchema();
            ResultSet rs = metaData.getTables(null, schema == null ? null
                    : schema.getSchemaName().toUpperCase(), "%", null);
            Statement statement = connection.createStatement();
            while (rs.next()) {
                String tableName = rs.getString(IDX_TABLE_NAME);
                log(Level.INFO, APP, GET_TABLES, "Table name: " + tableName);
                if (getTablesFromProperties().contains(tableName)) {
                    log(Level.INFO, APP, GET_TABLES, "Table name " + tableName + " belongs to the schema configuration.");
                    Table table = (schema == null) ? new Table(tableName)
                            : new Table(tableName, schema);
                    Select select = Select.createSelectInstance().tableFields()
                            .from(table);
                    ResultSet sResultSet = statement.executeQuery(select
                            .getQueryString());
                    ResultSetMetaData resultSetMetaData = sResultSet
                            .getMetaData();
                    log(Level.INFO, APP, GET_TABLES, "Statement > " + select.getQueryString());
                    int numberOfColumns = resultSetMetaData.getColumnCount();
                    log(Level.INFO, APP, GET_TABLES, tableName + " has " + numberOfColumns + " columns.");
                    for (int i = 1; i <= numberOfColumns; i++) {
                        String columnName = resultSetMetaData.getColumnName(i);
                        DataType columnTypeName = DataType
                                .getDataType(resultSetMetaData
                                        .getColumnTypeName(i));
                        int columnDisplaySize = resultSetMetaData
                                .getColumnDisplaySize(i);
                        int columnPrecision = resultSetMetaData.getPrecision(i);
                        int columnScale = resultSetMetaData.getScale(i);
                        boolean isNullable = resultSetMetaData.isNullable(i) == 1 ? TRUE
                                : FALSE;

                        TableColumn tableColumn = new TableColumn(columnName,
                                columnTypeName, columnDisplaySize,
                                columnPrecision, columnScale, isNullable);
                        log(Level.INFO, APP, GET_TABLES, tableName + "." + columnName);

                        table.addTableColumnName(columnName);
                        table.addTableColumn(tableColumn);
                        table.addToTableColumnMapping(columnName, tableColumn);
                    }
                    log(Level.INFO, APP, GET_TABLES, "Adding " + table.getTableName() + " to list of tables.");
                    tables.add(table);
                }
            }
        } catch (IOException | SQLException exc) {
            log(Level.SEVERE, APP, GET_TABLES, exc.getMessage());
        }

        for (Table table : tables) {
            for (String sequenceName : getSequences()) {
                if (sequenceName.toUpperCase().indexOf(
                        table.getTableName().toUpperCase()) == 0) {
                    table.setSequence(new Sequence(schema, sequenceName));
                    log(Level.INFO, APP, GET_TABLES, sequenceName + " configured for " + table.getTableName());
                }
            }
        }

        log(Level.INFO, APP, GET_TABLES, "Returning " + tables.size() + " tables.");
        return tables;
    }

    public static List<String> getSequences() {
        try {
            return getSequencesFromProperties();
        } catch (IOException exc) {
            log(Level.SEVERE, APP, GET_SEQUENCES, exc.getMessage());
        }
        return null;
    }

    private static List<String> getSequencesFromProperties()
            throws IOException, FileNotFoundException {
        if (sequenceNames == null) {
            sequenceNames = new ArrayList<>();
            log(Level.INFO, APP, GET_SEQUENCES_FROM_PROPERTIES, "Instantiating list of sequence names for application.");
        } else {
            return sequenceNames;
        }

        BufferedReader br = FileUtils.getFileReader(Property.getPropertyFile(
                PropertyPath.APPLICATION).getPathFile());
        String content = "";
        while ((content = br.readLine()) != null) {
            if (content.indexOf(SEQUENCE_IDX) == 0) {
                sequenceNames.add(content.split("=")[1]);
                log(Level.INFO, APP, GET_SEQUENCES_FROM_PROPERTIES, "Adding sequence " + content.split("=")[1] + " to list of sequences names.");
            }
        }

        return sequenceNames;
    }

    public static Table getTable(String tableName) {
        for (Table table : tables) {
            if (table.getTableName().equalsIgnoreCase(tableName)) {
                return table;
            }
        }
        return null;
    }

    public static List<TableColumn> getTableColumns(Table _table) {
        for (Table table : tables) {
            if (table == _table) {
                return table.getTableColumns();
            }
        }
        return null;
    }

    public static TableColumn getTableColumn(Table _table,
            String tableColumnName) {
        for (Table table : tables) {
            if (table == _table) {
                return table.getTableColumn(tableColumnName);
            }
        }
        return null;
    }

    private static List<String> getTablesFromProperties() throws IOException,
            FileNotFoundException {
        if (tableNames == null) {
            tableNames = new ArrayList<>();
            log(Level.INFO, APP, GET_TABLES_FROM_PROPERTIES, "Instantiating list of table names for application.");
        } else {
            return tableNames;
        }

        BufferedReader br = FileUtils.getFileReader(Property.getPropertyFile(
                PropertyPath.APPLICATION).getPathFile());
        String content = "";
        while ((content = br.readLine()) != null) {
            if (content.indexOf(TABLE_IDX) == 0) {
                tableNames.add(content.split("=")[1]);
                log(Level.INFO, APP, GET_TABLES_FROM_PROPERTIES, "Adding table " + content.split("=")[1] + " to list of table names.");
            }
        }

        return tableNames;
    }

    public static User getLoggedUser() {
        log(Level.INFO, APP, GET_LOGGED_USER, "Getting user from session configuration.");
        return Session.get().getLoggedUser();
    }

    public static void setLoggedUser(User user) {
        log(Level.INFO, APP, SET_LOGGED_USER, "Setting user for session configuration.");
        Session.get().setLoggedUser(user);
    }

    public static void log(Level level, String sourceClass, String sourceMethod, String logMessage) {
        LOG.logp(level, sourceClass, sourceMethod, logMessage);
    }

}
