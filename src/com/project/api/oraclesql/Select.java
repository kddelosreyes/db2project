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
    public static final String ON = "ON ";

    public static final String LEFT_JOIN = "LEFT JOIN ";
    public static final String RIGHT_JOIN = "RIGHT JOIN ";
    public static final String INNER_JOIN = "INNER JOIN ";
    public static final String JOIN = "JOIN ";
    public static final String OUTER_JOIN = "OUTER JOIN ";

    private Select() {
        if (queryString == null) {
            queryString = new StringBuilder(SELECT);
        }
    }

    public static Select createSelectInstance() {
        return new Select();
    }

    public Select tableFields() {
        queryString.append("* ");
        return this;
    }

    public Select tableFields(List<TableColumn> tableColumns, String tableName) {
        for (TableColumn tableColumn : tableColumns) {
            queryString.append(tableName).append(".").append(tableColumn.getColumnName()).append(", ");
        }
        queryString.deleteCharAt(queryString.length() - 2);
        return this;
    }

    private void getTableSchema(Table table) {
        queryString.append(table.getSchema() == null
                ? table.getTableName()
                : table.getSchema().getSchemaName() + "." + table.getTableName());
    }

    public Select from(Table table) {
        queryString.append(FROM);
        getTableSchema(table);
        return this;
    }

    public Select join(Table table, JoinType joinType) {
        switch (joinType) {
            case INNER_JOIN:
                innerJoin();
                break;
            case OUTER_JOIN:
                outerJoin();
                break;
            case LEFT_JOIN:
                leftJoin();
                break;
            case RIGHT_JOIN:
                rightJoin();
                break;
            case JOIN:
                join();
                break;
        }
        getTableSchema(table);
        return this;
    }

    private void leftJoin() {
        queryString.append(LEFT_JOIN);
    }

    private void rightJoin() {
        queryString.append(RIGHT_JOIN);
    }

    private void innerJoin() {
        queryString.append(INNER_JOIN);
    }

    private void join() {
        queryString.append(JOIN);
    }

    private void outerJoin() {
        queryString.append(OUTER_JOIN);
    }

    public Select on(TableColumn rightColumn, TableColumn leftColumn) {
        queryString.append(rightColumn.getColumnName() + " = " + leftColumn.getColumnName());
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

    public String getQueryString() {
        return queryString.toString();
    }

}
