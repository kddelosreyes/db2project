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
    private static List<Table> tables = null;
    private static Map<Class, Table> classTableMapping = null;
    private static List<String> tableNames = null;

    private static final String PROJECT_FILE = "project.properties";
    
    private static final int IDX_TABLE_NAME = 3;

    public static Connection getConnection() throws IOException, FileNotFoundException {
        if (oracleConnection == null) {
            oracleConnection = new OracleConnection();
        }

        return oracleConnection.getConnection();
    }
    
    public static Map<Class, Table> getClassTableMapping {
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
            ResultSet rs = metaData.getTables(null,
            		oracleConnection.getConnectionConfiguration().getSchema().getSchemaName(),
            		"%",
            		null);
            Statement statement = connection.createStatement();
            while(rs.next()) {
            	String tableName = rs.getString(IDX_TABLE_NAME);
            	if(getTablesFromConfig().contains(tableName)) {
            		Table table = new Table(tableName);
            		Select select = Select.createSelectInstance().tableFields().from(table);
            		ResultSet sResultSet = statement.executeQuery(select.getQueryString());
            		ResultSetMetaData metaData = sResultSet.getMetaData();
            		int numberOfColumns = metaData.getColumnCount();
            		for(int i = 1; i <= numberOfColumns; i++) {
            			String columnName = metaData.getColumnName(i);
            			DataType columnTypeName = DataType.getDataType(metaData.getColumnTypeName(i));
            			int columnDisplaySize = Integer.valueOf(metaData.getColumnDisplaySize(i));
            			boolean isNullable = Boolean.valueOf(metaData.isNullable(i));
            			TableColumn tableColumn = new TableColumn(columnName, columnTypeName, columnDisplaySize, isNullable);
            			
            			table.addTableColumnName(columnName);
            			table.addTableColumn(tableColumn);
            			table.addToTableColumnMapping(columnName, tableColumn);
            		}
            	}
            }
        } catch (FileNotFoundException exc) {
            System.out.println("FileNotFoundException: " + exc.getMessage());
        } catch (IOException exc) {
            System.out.println("IOException: " + exc.getMessage());
        }

        return tables;
    }
    
    public static Table getTable(String tableName) {
    	for(Table table : tables) {
    		if(table.getTableName().equalsIgnoreCase(tableName)) {
    			return table;
    		}
    	}
    	return null;
    }
    
    public static ArrayList<TableColumn> getTableColumns(Table _table) {
    	for(Table table : tables) {
    		if(table == _table) {
    			return table.getTableColumns();
    		}
    	}
    	return null;
    }
    
    public static TableColumn getTableColumn(Table _table, String tableColumnName) {
    	for(Table table : tables) {
    		if(table == _table) {
    			return table.getTableColumn(tableColumnName);
    		}
    	}
    	return null;
    }

    private static List<String> getTablesFromConfig() throws IOException, FileNotFoundException {
    	if(tableNames == null) {
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
