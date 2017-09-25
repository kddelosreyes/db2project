/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.app;

import com.project.api.oraclesql.DataType;
import com.project.api.oraclesql.OracleConnection;
import com.project.api.oraclesql.Schema;
import com.project.api.oraclesql.Select;
import com.project.api.oraclesql.Table;
import com.project.api.oraclesql.TableColumn;
import com.project.api.utils.FileUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class App {

    private static OracleConnection oracleConnection = null;
    private static List<Table> tables = null;
    private static Map<Class, Table> classTableMapping = null;
    private static List<String> tableNames = null;

    private static final String PROJECT_FILE = "project.properties";

    private static final int IDX_TABLE_NAME = 3;
    
    private static final Boolean TRUE = true;
    private static final Boolean FALSE = false;

    public static Connection getConnection() throws IOException, FileNotFoundException {
        if (oracleConnection == null) {
            oracleConnection = new OracleConnection();
        }

        return oracleConnection.getConnection();
    }

    public static Map<Class, Table> getClassTableMapping() {
        return classTableMapping;
    }

    public static List<Table> getTables() {
        if (tables == null) {
            tables = new ArrayList<>();
            classTableMapping = new HashMap<>();
        } else {
            return tables;
        }

        try {
            Connection connection = getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            Schema schema = oracleConnection.getConnectionConfiguration().getSchema();
            ResultSet rs = metaData.getTables(null,
                    schema.getSchemaName().toUpperCase(),
                    "%",
                    null);
            Statement statement = connection.createStatement();
            while (rs.next()) {
                String tableName = rs.getString(IDX_TABLE_NAME);
                if (getTablesFromConfig().contains(tableName)) {
                    @SuppressWarnings("null")
                    Table table = (schema == null) ? new Table(tableName) : new Table(tableName, schema);
                    Select select = Select.createSelectInstance().tableFields().from(table);
                    ResultSet sResultSet = statement.executeQuery(select.getQueryString());
                    ResultSetMetaData resultSetMetaData = sResultSet.getMetaData();
                    int numberOfColumns = resultSetMetaData.getColumnCount();
                    for (int i = 1; i <= numberOfColumns; i++) {
                        String columnName = resultSetMetaData.getColumnName(i);
                        DataType columnTypeName = DataType.getDataType(resultSetMetaData.getColumnTypeName(i));
                        int columnDisplaySize = resultSetMetaData.getColumnDisplaySize(i);
                        boolean isNullable = resultSetMetaData.isNullable(i) == 1 ? TRUE : FALSE;
                        
                        TableColumn tableColumn = new TableColumn(columnName, columnTypeName, columnDisplaySize, isNullable);

                        table.addTableColumnName(columnName);
                        table.addTableColumn(tableColumn);
                        table.addToTableColumnMapping(columnName, tableColumn);
                    }
                    tables.add(table);
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.println("FileNotFoundException: " + exc.getMessage());
        } catch (IOException exc) {
            System.out.println("IOException: " + exc.getMessage());
        } catch (SQLException exc) {
            System.out.println("SQLException: " + exc.getMessage());
        }

        return tables;
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

    public static TableColumn getTableColumn(Table _table, String tableColumnName) {
        for (Table table : tables) {
            if (table == _table) {
                return table.getTableColumn(tableColumnName);
            }
        }
        return null;
    }

    private static List<String> getTablesFromConfig() throws IOException, FileNotFoundException {
        if (tableNames == null) {
            tableNames = new ArrayList<>();
        } else {
            return tableNames;
        }

        BufferedReader br = FileUtils.getFileReader(PROJECT_FILE);
        String content = "";
        while ((content = br.readLine()) != null) {
            tableNames.add(content);
        }

        return tableNames;
    }

}
