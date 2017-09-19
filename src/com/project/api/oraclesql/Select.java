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
public class Select {

    private StringBuilder queryString = null;

    public static final String SELECT = "SELECT ";
    public static final String FROM = "FROM ";

    private Select() {
        if (queryString == null) {
            queryString = new StringBuilder(SELECT);
        }
    }

    public static Select createInstance() {
        return new Select();
    }

    public Select tableFields(List<TableColumn> tableColumns, String tableName) {
        for(TableColumn tableColumn : tableColumns) {
            queryString.append(tableName).append(".").append(tableColumn.getColumnName()).append(", ");
        }
        queryString.deleteCharAt(queryString.length() - 2);
        return this;
    }

    public Select from(Table table) {
        queryString.append(FROM);
        queryString.append(table.getTableName());
        return this;
    }

    public Select leftJoin() {
        return this;
    }

    public Select rightJoin() {
        return this;
    }

    public Select innerJoin() {
        return this;
    }

    public Select join() {
        return this;
    }

    public Select on() {
        return this;
    }

    public Select where() {
        return this;
    }

    public Select whereOr() {
        return this;
    }

    public Select whereAnd() {
        return this;
    }

    public Select whereNot() {
        return this;
    }

    public Select whereIn() {
        return this;
    }

    public Select whereNotIn() {
        return this;
    }

    public Select whereIsNull() {
        return this;
    }

    public Select whereIsNotNull() {
        return this;
    }

    public Select whereLike(String expression) {
        return this;
    }

    public Select orderBy(OrderType orderType) {
        return this;
    }

    public Select having() {
        return this;
    }

    public Select groupBy() {
        return this;
    }

}
