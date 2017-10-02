/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import java.util.List;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Insert {

    private StringBuilder queryString = null;

    public static final String INSERT = "INSERT ";
    public static final String INTO = "INTO ";
    public static final String VALUES = "VALUES ";

    private Insert() {
        if (queryString == null) {
            queryString = new StringBuilder(INSERT);
        }
    }

    public static Insert createInsertInstance() {
        return new Insert();
    }

    public Insert into(Table table) {
        queryString.append(INTO);
        queryString.append(table.getSchema() == null
                ? table.getTableName()
                : table.getSchema().getSchemaName() + "." + table.getTableName());
        return this;
    }

    public Insert into(List<Column> tableColumns) {
        queryString.append(INTO + "(");
        for (int idx = 0; idx < tableColumns.size(); idx++) {
            queryString.append(tableColumns.get(idx).getColumnName());
            if (idx != tableColumns.size() - 1) {
                queryString.append(",");
            }
        }
        queryString.append(") ");
        return this;
    }

    public Insert values(List<Object> values) {
        queryString.append(VALUES + "(");
        for (int idx = 0; idx < values.size(); idx++) {
            queryString.append(values.get(idx));
            if (idx != values.size() - 1) {
                queryString.append(",");
            }
        }
        queryString.append(")");
        return this;
    }

}
