/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import com.project.api.models.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Table {
    
    private StringBuilder queryCreateTable;
    private String tableName;
    private Schema schema;
    private Sequence sequence;
    private List<String> tableColumnNames;
    private List<TableColumn> tableColumns;
    private Map<String, TableColumn> tableColumnMapping;
    private List<Constraint> tableConstraints;
    private List<Item> tableItems;
    
    public Table() {
        queryCreateTable = new StringBuilder();
        tableColumnNames = new ArrayList<>();
        tableColumns = new ArrayList<>();
        tableColumnMapping = new HashMap<>();
        tableConstraints = new ArrayList<>();
        tableItems = new ArrayList<>();
    }
    
    public Table(String tableName) {
        this();
        this.tableName = tableName;
    }
    
    public Table(String tableName, Schema schema) {
        this(tableName);
        this.schema = schema;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public Schema getSchema() {
        return schema;
    }
    
    public void setSchema(Schema schema) {
        this.schema = schema;
    }
    
    public Sequence getSequence() {
        return sequence;
    }
    
    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
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
    
    public List<Item> getTableItems() {
        return tableItems;
    }
    
}
