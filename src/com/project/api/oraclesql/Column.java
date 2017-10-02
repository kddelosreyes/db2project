/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import com.project.api.models.constants.OracleConstants;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Column {

    private String columnName;
    private DataType dataType;
    private Integer length;
    private Integer precision;
    private Integer scale;
    private Boolean isNull;

    public Column(String columnName, DataType dataType,
            Integer length, Integer precision,
            Integer scale, Boolean isNull) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.length = length;
        this.precision = precision;
        this.scale = scale;
        this.isNull = isNull;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPresision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Boolean getIsNull() {
        return isNull;
    }

    public void setIsNull(Boolean isNull) {
        this.isNull = isNull;
    }

    @Override
    public String toString() {
        return getColumnName() + " " + getDataType().getValue()
                + "(" + getLength() + ")"
                + (isNull ? "" : " " + OracleConstants.SQL_NOT_NULL);
    }

}
