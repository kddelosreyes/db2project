/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Table {
    
    private StringBuilder queryCreateTable;
    private String tableName;
    private List<String> tableColumnNames;
    private List<TableColumn> tableColumns;
    private Map<String, TableColumn> tableColumnMapping;
    private List<Constraint> tableConstraints;
    
    public Table(String tableName) {
        this.tableName = tableName;
        queryCreateTable = new StringBuilder();
        tableColumnNames = new ArrayList<>();
        tableColumns = new ArrayList<>();
        tableColumnMapping = new HashMap<>();
        tableConstraints = new ArrayList<>();
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public void addTableColumnName(String tableColumnName) {
    	tableColumnNames.add(tableColumnName);
    }
    
    public void addTableColumn(TableColumn tableColumn) {
        tableColumns.add(tableColumn);
    }
    
    public void addToTableColumnMapping(String tableColumnName, TableColumn tableColumn) {
    	tableColumnMapping.put(tableColumnName, tableColumn);
    }
    
    public void addTableConstraint(Constraint constraint) {
        tableConstraints.add(constraint);
    }
    
    public String getQuery() {
        return queryCreateTable.toString();
    }
    
    public List<String> getTableColumnNames() {
    	return tableColumnNames;
    }
    
    public List<TableColumn> getTableColumns() {
        return tableColumns;
    }
    
    public Map<String, TableColumn> getTableColumnMapping() {
    	return tableColumnMapping;
    }
    
    public List<Constraint> getTableConstraints() {
        return tableConstraints;
    }
    
    public TableColumn getTableColumn(String tableColumnName) {
    	return tableColumnMapping.get(tableColumnName);
    }
    
}
